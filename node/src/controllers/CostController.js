const { validationResult } = require('express-validator');
const { getLocationByCep, getDistanceMatrix } = require('../services/googleService');

const calcFreightCost = async (req, res, next) => {
  // const errors = validationResult(req);
  // if (!errors.isEmpty()) {
  //   return res.status(400).json({ errors: errors.array() });
  // }
  getLocationByCep('88730000')
  getDistanceMatrix()

  res.send('Hi the cost is: 123123')
};


module.exports.calcFreightCost = calcFreightCost;