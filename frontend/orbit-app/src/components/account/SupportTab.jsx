import React, { useState, useEffect } from 'react';
import { supportAPI } from '../../services/api';

const SupportTab = ({ orderContext }) => {
  const [subject, setSubject] = useState('');
  const [message, setMessage] = useState('');
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState('');

  useEffect(() => {
    loadTickets();
  }, []);

  useEffect(() => {
    // Pre-fill subject if order context is provided
    if (orderContext) {
      setSubject(`Issue with ${orderContext}`);
      setMessage(`I need help with order ${orderContext}.\n\n`);
    }
  }, [orderContext]);

  const loadTickets = async () => {
    try {
      const response = await supportAPI.getTickets();
      setTickets(response.data);
    } catch (error) {
      console.error('Error loading tickets:', error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setSuccess('');

    try {
      // const response = await supportAPI.submitTicket(subject, message);
      setSuccess('Support ticket submitted successfully!');
      setSubject('');
      setMessage('');
      loadTickets();
    } catch (error) {
      console.error('Error submitting ticket:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="support-tab">
      <h3 className="tab-title">
        {orderContext ? `Issue with ${orderContext}` : 'Customer Support'}
      </h3>
      
      <div className="support-section">
        <h4 className="section-subtitle">Submit a Support Ticket</h4>
        
        {success && <div className="success-message">{success}</div>}
        
        <form onSubmit={handleSubmit} className="support-form">
          <div className="form-group">
            <label className="form-label">Subject</label>
            <input
              type="text"
              className="form-input"
              value={subject}
              onChange={(e) => setSubject(e.target.value)}
              placeholder="Brief description of your issue"
              required
            />
          </div>
          
          <div className="form-group">
            <label className="form-label">Message</label>
            <textarea
              className="form-textarea"
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              placeholder="Please provide details about your issue..."
              rows="5"
              required
            />
          </div>
          
          <button type="submit" className="submit-button" disabled={loading}>
            {loading ? 'Submitting...' : 'Submit Ticket'}
          </button>
        </form>
      </div>

      <div className="tickets-section">
        <h4 className="section-subtitle">Your Support Tickets</h4>
        {tickets.length === 0 ? (
          <p className="empty-state">No support tickets yet</p>
        ) : (
          <div className="tickets-list">
            {tickets.map(ticket => (
              <div key={ticket.id} className="ticket-card">
                <div className="ticket-header">
                  <div>
                    <h5 className="ticket-id">{ticket.ticketId}</h5>
                    <p className="ticket-subject">{ticket.subject}</p>
                  </div>
                  <span className={`ticket-status ${ticket.status.toLowerCase()}`}>
                    {ticket.status}
                  </span>
                </div>
                <p className="ticket-date">Submitted on {ticket.date}</p>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default SupportTab;
