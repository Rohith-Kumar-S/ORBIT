import React from 'react';
import { FaTruck, FaShieldAlt, FaBox } from 'react-icons/fa';

const HeroSection = () => {
  return (
    <div className="hero">
      <h2>Welcome to Orbit</h2>
      <p>Discover amazing products at great prices</p>
      <div className="hero-features">
        <div className="hero-feature">
          <FaTruck size={20} />
          <span>Free Shipping</span>
        </div>
        <div className="hero-feature">
          <FaShieldAlt size={20} />
          <span>Secure Payment</span>
        </div>
        <div className="hero-feature">
          <FaBox size={20} />
          <span>Easy Returns</span>
        </div>
      </div>
    </div>
  );
};

export default HeroSection;