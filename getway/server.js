const express = require('express');
const httpProxy = require('express-http-proxy');
const app = express();
const port = 3000;

const NODE_API = 'http://localhost:3332'
const JAVA_API = 'http://localhost:3002'

const nodeProxy = httpProxy(NODE_API);
const javaProxy = httpProxy(JAVA_API);

app.get('/', (req, res) => res.send('Hello Gateway API'));

app.get('/users', (req, res, next) => nodeProxy(req, res, next));
app.get('/products', (req, res, next) => javaProxy(req, res, next));

app.listen(port, () => console.log(`Example app listening on port ${port}!`));