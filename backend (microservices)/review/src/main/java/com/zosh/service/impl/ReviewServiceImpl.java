package com.zosh.service.impl;


import com.zosh.modal.Review;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.UserDTO;
import com.zosh.payload.request.CreateReviewRequest;
import com.zosh.repository.ReviewRepository;

import com.zosh.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    @Override
    public Review createReview(CreateReviewRequest req,
                               UserDTO user,
                               BusinessDTO business) {
        Review newReview = new Review();

        newReview.setReviewText(req.getReviewText());
        newReview.setRating(req.getReviewRating());
        newReview.setUserId(user.getId());
        newReview.setBusinessId(business.getId());

        return reviewRepository.save(newReview);
    }

    @Override
    public List<Review> getReviewsByBusinessId(Long productId) {
        return reviewRepository.findReviewsByBusinessId(productId);
    }


    @Override
    public Review updateReview(Long reviewId,
                               String reviewText,
                               double rating,
                               Long userId) throws Exception, AuthenticationException {
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(()-> new Exception("Review Not found"));

        if(review.getUserId()!=userId){
            throw new AuthenticationException("You do not have permission to delete this review");
        }

        review.setReviewText(reviewText);
        review.setRating(rating);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId,Long userId) throws Exception,
            AuthenticationException {
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(()-> new Exception("Review Not found"));
        if(review.getUserId()!=userId){
            throw new AuthenticationException("You do not have permission to delete this review");
        }
        reviewRepository.delete(review);
    }

}
