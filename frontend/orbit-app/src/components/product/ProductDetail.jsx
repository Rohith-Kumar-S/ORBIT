// ============================================
// src/components/product/ProductDetail.jsx (WITH REVIEWS)
// ============================================
import React, { useState, useEffect } from 'react';
import { FaStar, FaStarHalfAlt, FaRegStar, FaTruck, FaShieldAlt, FaBox, FaArrowLeft } from 'react-icons/fa';
import { useCart } from '../../context/CartContext';
import { useAuth } from '../../context/AuthContext';

const ProductDetail = ({ product, onNavigate }) => {
  const { addToCart } = useCart();
  const { user, isAuthenticated } = useAuth();
  const [reviews, setReviews] = useState([]);
  const [userRating, setUserRating] = useState(0);
  const [userReview, setUserReview] = useState('');
  const [hoverRating, setHoverRating] = useState(0);

  useEffect(() => {
    loadReviews();
  }, [product.id]);

  const loadReviews = () => {
    // Dummy reviews
    const dummyReviews = [
      {
        id: 1,
        userName: 'Sarah M.',
        rating: 5,
        date: 'October 20, 2024',
        comment: 'Excellent product! Highly recommend.',
        verified: true
      },
      {
        id: 2,
        userName: 'Michael K.',
        rating: 4,
        date: 'October 18, 2024',
        comment: 'Good quality, but shipping took longer than expected.',
        verified: true
      },
      {
        id: 3,
        userName: 'Emily R.',
        rating: 5,
        date: 'October 15, 2024',
        comment: 'Amazing! Exactly as described. Very happy with my purchase.',
        verified: true
      }
    ];
    setReviews(dummyReviews);
  };

  const handleSubmitReview = (e) => {
    e.preventDefault();
    
    if (!isAuthenticated) {
      alert('Please log in to submit a review');
      onNavigate('login');
      return;
    }

    if (userRating === 0) {
      alert('Please select a rating');
      return;
    }

    const newReview = {
      id: reviews.length + 1,
      userName: user.name,
      rating: userRating,
      date: new Date().toLocaleDateString('en-US', { year: 'numeric', month: 'long', day: 'numeric' }),
      comment: userReview,
      verified: true
    };

    setReviews([newReview, ...reviews]);
    setUserRating(0);
    setUserReview('');
  };

  const renderStars = (rating, interactive = false) => {
    const stars = [];
    const fullStars = Math.floor(rating);
    const hasHalfStar = rating % 1 >= 0.5;

    for (let i = 1; i <= 5; i++) {
      if (interactive) {
        const isFilled = i <= (hoverRating || userRating);
        stars.push(
          <FaStar
            key={i}
            className={`interactive-star ${isFilled ? 'filled' : ''}`}
            onClick={() => setUserRating(i)}
            onMouseEnter={() => setHoverRating(i)}
            onMouseLeave={() => setHoverRating(0)}
          />
        );
      } else {
        if (i <= fullStars) {
          stars.push(<FaStar key={i} className="review-star filled" />);
        } else if (i === fullStars + 1 && hasHalfStar) {
          stars.push(<FaStarHalfAlt key={i} className="review-star half" />);
        } else {
          stars.push(<FaRegStar key={i} className="review-star" />);
        }
      }
    }
    return stars;
  };

  const isImageUrl = (image) => {
    return image && (image.startsWith('http') || image.startsWith('/'));
  };

  const getImageSource = (product) => {
    return product.imgUrl || product.image;
  };

  const getProductName = (product) => {
    return product.title || product.name;
  };

  const imageSource = getImageSource(product);
  const productName = getProductName(product);

  return (
    <div className="page-container">
      <button className="back-button" onClick={() => onNavigate('home')}>
        <FaArrowLeft size={20} />
        Back to Shop
      </button>
      <div className="product-detail-card">
        <div className="product-detail-grid">
          <div className="product-detail-image">
            {isImageUrl(imageSource) ? (
              <img src={imageSource} alt={productName} />
            ) : (
              imageSource
            )}
          </div>
          <div>
            <span className="product-category">{product.category}</span>
            <h2 className="product-detail-title">{productName}</h2>
            <div className="product-detail-rating">
              <FaStar className="single-rating-star" size={20} />
              <span className="rating-text">{product.rating} / 5.0</span>
              <span className="rating-count">({reviews.length} reviews)</span>
            </div>
            <p className="product-description">{product.description}</p>
            <div className="product-detail-price">${product.price}</div>
            <button
              className="add-to-cart-large-btn"
              onClick={() => {
                addToCart(product);
              }}
            >
              Add to Cart
            </button>
            <div className="product-features">
              <div className="product-feature">
                <FaTruck size={20} />
                <span>Free shipping on orders over $50</span>
              </div>
              <div className="product-feature">
                <FaShieldAlt size={20} />
                <span>2-year warranty included</span>
              </div>
              <div className="product-feature">
                <FaBox size={20} />
                <span>30-day return policy</span>
              </div>
            </div>
          </div>
        </div>

        {/* Reviews Section */}
        <div className="reviews-section">
          <h3 className="reviews-title">Customer Reviews</h3>
          
          {/* Write a Review */}
          <div className="write-review-section">
            <h4 className="write-review-title">Write a Review</h4>
            {isAuthenticated ? (
              <form onSubmit={handleSubmitReview} className="review-form">
                <div className="rating-input-section">
                  <label className="review-label">Your Rating</label>
                  <div className="star-rating-input">
                    {renderStars(hoverRating || userRating, true)}
                  </div>
                </div>
                <div className="review-input-section">
                  <label className="review-label">Your Review</label>
                  <textarea
                    className="review-textarea"
                    value={userReview}
                    onChange={(e) => setUserReview(e.target.value)}
                    placeholder="Share your thoughts about this product..."
                    rows="4"
                    required
                  />
                </div>
                <button type="submit" className="submit-review-btn">
                  Submit Review
                </button>
              </form>
            ) : (
              <div className="login-prompt">
                <p>Please log in to write a review</p>
                <button 
                  className="login-to-review-btn"
                  onClick={() => onNavigate('login')}
                >
                  Log In
                </button>
              </div>
            )}
          </div>

          {/* Reviews List */}
          <div className="reviews-list">
            {reviews.length === 0 ? (
              <p className="no-reviews">No reviews yet. Be the first to review!</p>
            ) : (
              reviews.map(review => (
                <div key={review.id} className="review-card">
                  <div className="review-header">
                    <div className="reviewer-info">
                      <span className="reviewer-name">{review.userName}</span>
                      {review.verified && (
                        <span className="verified-badge">Verified Purchase</span>
                      )}
                    </div>
                    <span className="review-date">{review.date}</span>
                  </div>
                  <div className="review-rating-display">
                    {renderStars(review.rating)}
                  </div>
                  <p className="review-comment">{review.comment}</p>
                </div>
              ))
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;