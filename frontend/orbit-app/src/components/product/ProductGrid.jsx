import React from 'react';
import ProductCard from './ProductCard';

const ProductGrid = ({ products, onViewProduct }) => {
  return (
    <div className="product-grid">
      {products.map(product => (
        <ProductCard 
          key={product.id} 
          product={product} 
          onViewProduct={onViewProduct}
        />
      ))}
    </div>
  );
};

export default ProductGrid;