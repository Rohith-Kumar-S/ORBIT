import React from 'react';
import { FaArrowLeft } from 'react-icons/fa';
import { useCart } from '../context/CartContext';
import CartItem from '../components/cart/CartItem';
import CartSummary from '../components/cart/CartSummary';
import EmptyCart from '../components/cart/EmptyCart';

const CartPage = ({ onNavigate }) => {
  const { cart } = useCart();

  return (
    <div className="page-container">
      <button className="back-button" onClick={() => onNavigate('home')}>
        <FaArrowLeft size={20} />
        Continue Shopping
      </button>
      <h2 className="cart-header">Shopping Cart</h2>
      {cart.length === 0 ? (
        <EmptyCart />
      ) : (
        <div className="cart-layout">
          <div className="cart-items">
            {cart.map(item => (
              <CartItem key={item.id} item={item} />
            ))}
          </div>
          <CartSummary onCheckout={() => onNavigate('checkout')} items={cart} onNavigate={onNavigate} />
        </div>
      )}
    </div>
  );
};

export default CartPage;
