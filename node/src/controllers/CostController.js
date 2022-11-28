const { validationResult } = require('express-validator');

const calcFreightCost = async (req, res, next) => {
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    return res.status(400).json({ errors: errors.array() });
  }

  
  res.send('Hi the cost is: 123123')
};


module.exports.calcFreightCost = calcFreightCost;