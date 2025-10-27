import React, { createContext, useState, useContext } from 'react';
import { cartsAPI } from '../services/cartService';

const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);

  const proceedToCheckout = async (items) => {
    const response = await cartsAPI.proceedToCheckout(items);
    return response;
  };
    
    const getPricing = async () => {
      try {
        const response = await cartsAPI.getPricing();
        if (response.data.success) {
          return response.data;
        }
        else {
          return {success: false, error: response.data.message || 'Failed to fetch pricing' };
        }
      } catch (error) {
        console.error('Error fetching pricing:', error);
        return { success: false, error: error.message || 'Failed to fetch pricing' };
      }
    };

    const proceedToBuy = async () => {
      try {
        const response = await cartsAPI.proceedToBuy();
         if (response.data.success) {
          return response.data;
        }
        else {
          return {success: false, error: response.data.message || 'Buy process failed' };
        }
      } catch (error) {
        console.error('Error during buy process:', error);
        return { success: false, error: error.message || 'Buy process failed' };
      }
    };

  const addToCart = (product) => {
    const existingItem = cart.find(item => item.id === product.id);
    if (existingItem) {
      setCart(cart.map(item => 
        item.id === product.id ? { ...item, quantity: item.quantity + 1 } : item
      ));
    } else {
      setCart([...cart, { ...product, quantity: 1 }]);
    }
  };

  // NEW: Add multiple items at once (for Buy Again feature)
  const addMultipleToCart = (items) => {
    setCart(currentCart => {
      const newCart = [...currentCart];
      
      items.forEach(item => {
        const existingIndex = newCart.findIndex(cartItem => cartItem.id === item.id);
        
        if (existingIndex !== -1) {
          // Item exists in cart, increase quantity
          newCart[existingIndex] = {
            ...newCart[existingIndex],
            quantity: newCart[existingIndex].quantity + item.quantity
          };
        } else {
          // New item, add with its quantity
          newCart.push({ ...item, quantity: item.quantity });
        }
      });
      
      return newCart;
    });
  };

  const updateQuantity = (id, change) => {
    setCart(cart.map(item => {
      if (item.id === id) {
        const newQuantity = item.quantity + change;
        return newQuantity > 0 ? { ...item, quantity: newQuantity } : item;
      }
      return item;
    }).filter(item => item.quantity > 0));
  };

  const removeFromCart = (id) => {
    setCart(cart.filter(item => item.id !== id));
  };

  const getTotalPrice = () => {
    return cart.reduce((sum, item) => sum + item.price * item.quantity, 0).toFixed(2);
  };

  const getTotalItems = () => {
    return cart.reduce((sum, item) => sum + item.quantity, 0);
  };

  const clearCart = () => {
    setCart([]);
  };

  return (
    <CartContext.Provider value={{
      cart,
      addToCart,
      addMultipleToCart,
      updateQuantity,
      removeFromCart,
      getTotalPrice,
      getTotalItems,
      proceedToBuy,
      getPricing,
      proceedToCheckout,
      clearCart
    }}>
      {children}
    </CartContext.Provider>
  );
};

export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('useCart must be used within CartProvider');
  }
  return context;
};