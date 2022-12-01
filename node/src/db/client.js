const { Client } = require('pg')

  const client = new Client('postgresql://postgres:aPIm6yOVcxVYzvgKx5rf@containers-us-west-96.railway.app:5896/railway')
  client
  .connect()
  .then(() => console.log('connected'))
  .catch((err) => console.error('connection error', err.stack))

module.exports = { client }