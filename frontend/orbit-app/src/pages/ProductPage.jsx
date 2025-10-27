import React from 'react';
import ProductDetail from '../components/product/ProductDetail';

const ProductPage = ({ product, onNavigate }) => {
  return <ProductDetail product={product} onNavigate={onNavigate} />;
};

export default ProductPage;