package com.zosh.mapper;

import com.zosh.modal.Review;
import com.zosh.payload.dto.ReviewDTO;
import com.zosh.payload.dto.UserDTO;

public class ReviewMapper {

    public static ReviewDTO mapToDTO(Review review, UserDTO userDTO) {
        if (review == null) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setReviewText(review.getReviewText());
        reviewDTO.setRating(review.getRating());
//        reviewDTO.setBusinessId(review.getBusinessId());
        reviewDTO.setUser(userDTO);
        reviewDTO.setCreatedAt(review.getCreatedAt());

        return reviewDTO;
    }
}
