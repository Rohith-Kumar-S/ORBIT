import React, { createContext, useContext } from 'react';
import { productsAPI } from '../services/productService';

const ProductContext = createContext();

export const ProductProvider = ({ children }) => {

  const getPopularProducts = async (sellerId, page, productsCount) => {
    try {
        const response = await productsAPI.getPopularProducts(sellerId, page, productsCount);
        // console.log('Popular Products Response:', response);
        if (response.success) {
            return response.products.content;
        } else {
            // Handle error
            return [];
        }
    } catch (error) {
      console.error('Error loading popular products:', error);
    } finally {
      // Any cleanup actions
    }
  };

      
  return (
    <ProductContext.Provider value={{
      getPopularProducts
    }}>
      {children}
    </ProductContext.Provider>
  );
};

export const useProduct = () => {
  const context = useContext(ProductContext);
  if (!context) {
    throw new Error('useProduct must be used within ProductProvider');
  }
  return context;
};
