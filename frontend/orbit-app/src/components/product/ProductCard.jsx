import React from 'react';
import { FaStar } from 'react-icons/fa';
import { useCart } from '../../context/CartContext';

const ProductCard = ({ product, onViewProduct }) => {
  const { addToCart } = useCart();

  // Helper function to check if image is a URL or emoji
  const isImageUrl = (image) => {
    return image && (image.startsWith('http') || image.startsWith('/'));
  };

  return (
    <div 
      className="product-card"
      onClick={() => onViewProduct(product)}
    >
      <div className="product-image">
        {isImageUrl(product.image) ? (
          <img src={product.image} alt={product.name} />
        ) : (
          product.image
        )}
      </div>
      <div className="product-info">
        <h4 className="product-name">{product.name}</h4>
        <div className="product-rating">
          <FaStar size={16} />
          <span>{product.rating}</span>
        </div>
        <div className="product-footer">
          <span className="product-price">${product.price}</span>
          <button
            className="add-to-cart-btn"
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
  );
};

export default ProductCard;

