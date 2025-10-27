import React, { useState, useRef, useEffect } from 'react';
import { FaChevronLeft, FaChevronRight } from 'react-icons/fa';
import { FaStar } from 'react-icons/fa';
import { useCart } from '../../context/CartContext';

const ProductCarousel = ({ title, products, onViewProduct }) => {
  const [showLeftButton, setShowLeftButton] = useState(false);
  const [showRightButton, setShowRightButton] = useState(false);
  const carouselRef = useRef(null);
  const { addToCart } = useCart();

  // Helper function to check if image is a URL or emoji
  const isImageUrl = (image) => {
    return image && (image.startsWith('http') || image.startsWith('/'));
  };

  // Get image source - supports both API data (imgUrl) and demo data (image)
  const getImageSource = (product) => {
    return product.imgUrl;
  };

  // Get product name - supports both API data (title) and demo data (name)
  const getProductName = (product) => {
    return product.title || product.name;
  };

  const checkScroll = () => {
    if (carouselRef.current) {
      const { scrollLeft, scrollWidth, clientWidth } = carouselRef.current;
      setShowLeftButton(scrollLeft > 5);
      setShowRightButton(scrollLeft < scrollWidth - clientWidth - 5);
    }
  };

  useEffect(() => {
    // Check if carousel needs scrolling
    if (carouselRef.current) {
      const { scrollWidth, clientWidth } = carouselRef.current;
      const needsScroll = scrollWidth > clientWidth;
      setShowRightButton(needsScroll);
    }
    
    checkScroll();
    const carousel = carouselRef.current;
    if (carousel) {
      carousel.addEventListener('scroll', checkScroll);
      window.addEventListener('resize', checkScroll);
      return () => {
        carousel.removeEventListener('scroll', checkScroll);
        window.removeEventListener('resize', checkScroll);
      };
    }
  }, [products]);

  const scroll = (direction) => {
    if (carouselRef.current) {
      const scrollAmount = 1220;
      const newScrollLeft = direction === 'left' 
        ? carouselRef.current.scrollLeft - scrollAmount
        : carouselRef.current.scrollLeft + scrollAmount;
      
      carouselRef.current.scrollTo({
        left: newScrollLeft,
        behavior: 'smooth'
      });
    }
  };

  if (!products || products.length === 0) {
    return null;
  }

  return (
    <div className="product-carousel">
      <h3 className="product-carousel-title">{title}</h3>
      <div className="product-carousel-wrapper">
        {showLeftButton && (
          <button 
            className="carousel-button carousel-button-prev"
            onClick={() => scroll('left')}
            type="button"
          >
            <FaChevronLeft />
          </button>
        )}
        
        <div className="product-carousel-track" ref={carouselRef}>
          {products.map(product => {
            const imageSource = getImageSource(product);
            const productName = getProductName(product);

            return (
              <div key={product.id} className="product-carousel-item">
                <div 
                  className="product-carousel-card"
                  onClick={() => onViewProduct(product)}
                >
                  <div className="product-carousel-image">
                    {isImageUrl(imageSource) ? (
                      <img src={imageSource} alt={productName} />
                    ) : (
                      imageSource
                    )}
                  </div>
                  <div className="product-carousel-info">
                    <h4 className="product-carousel-name">{productName}</h4>
                    <div className="product-carousel-rating">
                      <FaStar size={14} />
                      <span>{product.rating}</span>
                    </div>
                    <div className="product-carousel-footer">
                      <span className="product-carousel-price">${product.price}</span>
                      <button
                        className="product-carousel-btn"
                        onClick={(e) => {
                          e.stopPropagation();
                          addToCart(product);
                        }}
                      >
                        Add to Cart
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            );
          })}
        </div>

        {showRightButton && (
          <button 
            className="carousel-button carousel-button-next"
            onClick={() => scroll('right')}
            type="button"
          >
            <FaChevronRight />
          </button>
        )}
      </div>
    </div>
  );
};

export default ProductCarousel;