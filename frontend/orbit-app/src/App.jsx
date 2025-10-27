import React, { useState } from 'react';
import { CartProvider } from './context/CartContext';
import { AuthProvider } from './context/AuthContext';
import Header from './components/common/Header';
import HomePage from './pages/HomePage';
import ProductPage from './pages/ProductPage';
import CartPage from './pages/CartPage';
import CheckoutPage from './pages/CheckoutPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import AccountPage from './pages/AccountPage';
import OrderSuccessPage from './pages/OrderSuccessPage';
import { ProductProvider } from './context/ProductContext';

const App = () => {
  const [currentPage, setCurrentPage] = useState('home');
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [orderDetails, setOrderDetails] = useState(null);

  const handleSelectProduct = (product) => {
    setSelectedProduct(product);
    setCurrentPage('product');
  };

  const handleOrderSuccess = (details) => {
    setOrderDetails(details);
    setCurrentPage('orderSuccess');
  };

  return (
    <AuthProvider>
      <ProductProvider>
      <CartProvider>
        <div style={{ minHeight: '100vh', backgroundColor: '#f9fafb' }}>
          <Header onNavigate={setCurrentPage} />
          
          {currentPage === 'home' && (
            <HomePage 
              onNavigate={setCurrentPage}
              onSelectProduct={handleSelectProduct}
            />
          )}
          
          {currentPage === 'product' && (
            <ProductPage 
              product={selectedProduct}
              onNavigate={setCurrentPage}
            />
          )}
          
          {currentPage === 'cart' && (
            <CartPage onNavigate={setCurrentPage} />
          )}
          
          {currentPage === 'checkout' && (
            <CheckoutPage 
              onNavigate={setCurrentPage}
              onOrderSuccess={handleOrderSuccess}
            />
          )}
          
          {currentPage === 'orderSuccess' && (
            <OrderSuccessPage 
              orderDetails={orderDetails}
              onNavigate={setCurrentPage}
            />
          )}
          
          {currentPage === 'login' && (
            <LoginPage onNavigate={setCurrentPage} />
          )}
          
          {currentPage === 'register' && (
            <RegisterPage onNavigate={setCurrentPage} />
          )}
          
          {currentPage === 'account' && (
            <AccountPage onNavigate={setCurrentPage} 
              onSelectProduct={handleSelectProduct}
            />
          )}
        </div>
      </CartProvider>
      </ProductProvider>
    </AuthProvider>
  );
};

export default App;