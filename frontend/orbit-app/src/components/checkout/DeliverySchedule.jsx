import React from 'react';

const DeliverySchedule = ({ formData, onChange }) => {
  return (
    <div className="checkout-section">
      <h3>Delivery Schedule</h3>
      <div className="form-row">
        <div className="form-group">
          <label className="form-label">Preferred Date</label>
          <input
            type="date"
            className="form-input"
            value={formData.date}
            onChange={(e) => onChange({ ...formData, date: e.target.value })}
          />
        </div>
        <div className="form-group">
          <label className="form-label">Preferred Time</label>
          <select
            className="form-select"
            value={formData.time}
            onChange={(e) => onChange({ ...formData, time: e.target.value })}
          >
            <option value="">Select time</option>
            <option value="morning">Morning (8AM - 12PM)</option>
            <option value="afternoon">Afternoon (12PM - 5PM)</option>
            <option value="evening">Evening (5PM - 8PM)</option>
          </select>
        </div>
      </div>
    </div>
  );
};

export default DeliverySchedule;
