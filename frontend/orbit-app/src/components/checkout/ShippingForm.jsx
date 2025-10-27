import React from 'react';

const ShippingForm = ({ formData, onChange }) => {
  return (
    <div className="checkout-section">
      <h3>Shipping Information</h3>
      <div className="form-group">
        <label className="form-label">Full Name</label>
        <input
          type="text"
          className="form-input"
          value={formData.name}
          onChange={(e) => onChange({ ...formData, name: e.target.value })}
          placeholder="John Doe"
          required
        />
      </div>
      <div className="form-group">
        <label className="form-label">Street Address</label>
        <input
          type="text"
          className="form-input"
          value={formData.address}
          onChange={(e) => onChange({ ...formData, address: e.target.value })}
          placeholder="123 Main St"
          required
        />
      </div>
      <div className="form-row">
        <div className="form-group">
          <label className="form-label">City</label>
          <input
            type="text"
            className="form-input"
            value={formData.city}
            onChange={(e) => onChange({ ...formData, city: e.target.value })}
            placeholder="New York"
            required
          />
        </div>
        <div className="form-group">
          <label className="form-label">ZIP Code</label>
          <input
            type="text"
            className="form-input"
            value={formData.zip}
            onChange={(e) => onChange({ ...formData, zip: e.target.value })}
            placeholder="10001"
            required
          />
        </div>
      </div>
    </div>
  );
};

export default ShippingForm;