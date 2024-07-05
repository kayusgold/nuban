import React, { useState, useEffect } from 'react';

function BankForm() {
  const [accountNumber, setAccountNumber] = useState('');
  const [suggestions, setSuggestions] = useState([]);
  const [banks, setBanks] = useState([]);
  const [selectedBank, setSelectedBank] = useState('');

  useEffect(() => {
    // Fetch all bank names on component mount
    fetch('/api/banks.php')
      .then(response => response.json())
      .then(data => setBanks(data))
      .catch(error => console.error('Error fetching banks:', error));
  }, []);

  const handleAccountNumberChange = (event) => {
    const value = event.target.value;
    if (/^\d{0,10}$/.test(value)) {
      setAccountNumber(value);

      if (value.length === 10) {
        // Fetch suggestions from backend
        fetch(`/api/index.php?accountNumber=${value}`)
          .then(response => response.json())
          .then(data => setSuggestions(data))
          .catch(error => console.error('Error fetching suggestions:', error));
      } else {
        setSuggestions([]);
      }
    }
  };

  const handleBankChange = (event) => {
    setSelectedBank(event.target.value);
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Enter 10-digit account number"
        value={accountNumber}
        onChange={handleAccountNumberChange}
      />
      {suggestions.length > 0 && (
        <div>
          <h3>Suggested Banks:</h3>
          <ul>
            {suggestions.map((bank, index) => (
              <li key={index} onClick={() => setSelectedBank(bank)}>
                {bank}
              </li>
            ))}
          </ul>
        </div>
      )}
      <select value={selectedBank} onChange={handleBankChange}>
        <option value="">Select a bank</option>
        {banks.map((bank, index) => (
          <option key={index} value={bank}>
            {bank}
          </option>
        ))}
      </select>
      {selectedBank && <p>Selected Bank: {selectedBank}</p>}
    </div>
  );
}

export default BankForm;
