package com.zosh.controller;


import com.zosh.exception.UserException;
import com.zosh.mapper.ReviewMapper;
import com.zosh.modal.Review;

import com.zosh.payload.dto.ReviewDTO;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.UserDTO;
import com.zosh.payload.request.CreateReviewRequest;
import com.zosh.payload.response.ApiResponse;

import com.zosh.service.ReviewService;
import com.zosh.service.clients.BusinessFeignClient;
import com.zosh.service.clients.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserFeignClient userService;
    private final BusinessFeignClient businessService;

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProductId(
            @PathVariable Long businessId) {

        List<Review> reviews = reviewService.getReviewsByBusinessId(businessId);

        List<ReviewDTO> reviewDTOS =  reviews.stream().map((review)->
                {
                    UserDTO user= null;
                    try {
                        user = userService.getUserById(review.getUserId()).getBody();
                    } catch (UserException e) {
                        throw new RuntimeException(e);
                    }
                    return ReviewMapper.mapToDTO(review,user);
                }
        ).toList();

        return ResponseEntity.ok(reviewDTOS);

    }

    @PostMapping("/business/{businessId}")
    public ResponseEntity<ReviewDTO> writeReview(
            @RequestBody CreateReviewRequest req,
            @PathVariable Long businessId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO user = userService.getUserFromJwtToken(jwt).getBody();
        BusinessDTO product = businessService.getBusinessById(businessId).getBody();


        Review review = reviewService.createReview(
                req, user, product
        );
        UserDTO reviewer = userService.getUserById(
                review.getUserId()
        ).getBody();

        ReviewDTO reviewDTO= ReviewMapper.mapToDTO(review,reviewer);

        return ResponseEntity.ok(reviewDTO);

    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @RequestBody CreateReviewRequest req,
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwt)
            throws Exception {

        UserDTO user = userService.getUserFromJwtToken(jwt).getBody();

        Review review = reviewService.updateReview(
                reviewId,
                req.getReviewText(),
                req.getReviewRating(),
                user.getId()
        );
        return ResponseEntity.ok(review);

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwt) throws Exception
            {

        UserDTO user = userService.getUserFromJwtToken(jwt).getBody();

        reviewService.deleteReview(reviewId, user.getId());
        ApiResponse res = new ApiResponse("Review deleted successfully");


        return ResponseEntity.ok(res);

    }
}
