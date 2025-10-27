import React, { useState, useEffect, useCallback } from 'react';
import { products } from '../data/products';
import { categories } from '../data/categories';
import HeroSection from '../components/home/HeroSection';
import CategoryFilter from '../components/home/CategoryFilter';
import ProductCarousel from '../components/home/ProductCarousel';
import { useProduct } from '../context/ProductContext';

const HomePage = ({ onNavigate, onSelectProduct }) => {
  const [selectedCategory, setSelectedCategory] = useState('Amazon');
  const { getPopularProducts } = useProduct();
  // const [loading, setLoading] = useState(false);
  const [popularProducts, setPopularProducts] = useState([]);
  useEffect(() => {
    setPopularProducts(products.filter(p => p.popular));
  }, []);
  // const getProductsByCategory = (category) => {
  //   if (category === 'All') return products;
  //   return products.filter(p => p.category === category);
  // };

  const loadPopularProducts = useCallback(async () => {
    const loadedProducts = await getPopularProducts(1, 0, 10);
    try {
      if (loadedProducts.length > 0) {
        setPopularProducts(loadedProducts);
      } else {
        // Handle error
        console.log('No popular products found');
      }
    } catch (error) {
      console.error('Error loading popular products:', error);
    }
  }, [getPopularProducts]);

   useEffect(() => {
      // Check if user is logged in on mount
      // setLoading(true);
      loadPopularProducts();
      // setLoading(false);
    }, [loadPopularProducts]);

 

  return (
    <div className="page-container">
      <HeroSection />
      <CategoryFilter 
        categories={categories}
        selected={selectedCategory}
        onSelect={setSelectedCategory}
      />

      <ProductCarousel 
        title="Top Products"
        products={popularProducts}
        onViewProduct={onSelectProduct}
      />

      {/* {selectedCategory === 'All' ? (
        <>
          <ProductCarousel 
            title="Electronics"
            products={getProductsByCategory('Electronics')}
            onViewProduct={onSelectProduct}
          />
          <ProductCarousel 
            title="Fashion"
            products={getProductsByCategory('Fashion')}
            onViewProduct={onSelectProduct}
          />
          <ProductCarousel 
            title="Home & Kitchen"
            products={getProductsByCategory('Home')}
            onViewProduct={onSelectProduct}
          />
          <ProductCarousel 
            title="Sports & Outdoors"
            products={getProductsByCategory('Sports')}
            onViewProduct={onSelectProduct}
          />
          <ProductCarousel 
            title="Accessories"
            products={getProductsByCategory('Accessories')}
            onViewProduct={onSelectProduct}
          />
        </>
      ) : (
        <ProductCarousel 
          title={selectedCategory}
          products={getProductsByCategory(selectedCategory)}
          onViewProduct={onSelectProduct}
        />
      )} */}
    </div>
  );
};

export default HomePage;