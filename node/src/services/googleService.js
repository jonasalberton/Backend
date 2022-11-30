const https = require('https');
const { googleApiKey, distanceMatrixApiKey } =  require('../../keys')

const getLocationByCep = async (cep) => {
  return new Promise((resolve, reject) => {
    https.get(`https://maps.googleapis.com/maps/api/geocode/json?key=${googleApiKey}&address=${cep}`, res => {
      let data = [];
  
      res.on('data', chunk => {
        data.push(chunk);
      });
    
      res.on('end', () => {
        const dt = JSON.parse(Buffer.concat(data).toString());
        resolve(dt)
      });

    }).on('error', err => {
      reject(err.message);
    });
  })
}

const getDistanceMatrix = async (origin, destination) => {
  return new Promise((resolve, reject) => {
    https.get(`https://maps.googleapis.com/maps/api/distancematrix/json?destinations=${destination}&origins=${origin}&key=${distanceMatrixApiKey}`, res => {
      let data2 = [];
  
      res.on('data', chunk => {
        data2.push(chunk);
      });
    
      res.on('end', () => {
        const dt2 = JSON.parse(Buffer.concat(data2).toString());
        resolve(dt2)
      });

    }).on('error', err => {
      reject(err.message);
    });
  })
}

module.exports = { getLocationByCep, getDistanceMatrix }