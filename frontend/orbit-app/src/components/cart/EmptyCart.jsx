import React from 'react';
import { FaShoppingCart } from 'react-icons/fa';

const EmptyCart = () => {
  return (
    <div className="empty-cart">
      <FaShoppingCart size={64} />
      <p>Your cart is empty</p>
    </div>
  );
};

export default EmptyCart;
