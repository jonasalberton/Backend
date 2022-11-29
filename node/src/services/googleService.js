const https = require('https');
const { googleApiKey } =  require('../../keys')

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

const getDistanceMatrix = async (cep) => {
  return new Promise((resolve, reject) => {
    https.get(`https://maps.googleapis.com/maps/api/distancematrix/json?destinations=-28.9462927%2C-49.4901548&origins=heading%3D90%3A-28.4860135%2C-49.0149725&key=AIzaSyCYXZOSWEbEYCPbxH3qtZ-DFH7T53wl_1I}`, res => {
      let data2 = [];
  
      res.on('data2', chunk => {
        data2.push(chunk);
      });
    
      res.on('end', () => {
        const dt2 = JSON.parse(Buffer.concat(data2).toString());
        console.log('dt2', dt2);
        resolve(dt2)
      });

    }).on('error', err => {
      reject(err.message);
    });
  })
}

module.exports = { getLocationByCep, getDistanceMatrix }