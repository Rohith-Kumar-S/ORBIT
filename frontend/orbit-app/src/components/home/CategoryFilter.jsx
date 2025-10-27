import React from 'react';

const CategoryFilter = ({ categories, selected, onSelect }) => {
  return (
    <div className="category-section">
      <h3>Shop Products from</h3>
      <div className="category-buttons">
        {categories.map(category => (
          <button
            key={category}
            onClick={() => onSelect(category)}
            className={`category-btn ${selected === category ? 'active' : ''}`}
          >
            {category}
          </button>
        ))}
      </div>
    </div>
  );
};

export default CategoryFilter;