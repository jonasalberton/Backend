const express = require('express');
const httpProxy = require('express-http-proxy');
const app = express();
const port = 3000;

const NODE_API = 'http://localhost:3332'
const JAVA_API = 'http://localhost:3002'

const nodeProxy = httpProxy(NODE_API);
const javaProxy = httpProxy(JAVA_API);

app.get('/', (req, res) => res.send('Hello Gateway API'));

// Tabelas
app.post('/tabelas', (req, res, next) => nodeProxy(req, res, next));
app.put('/tabelas/:id', (req, res, next) => nodeProxy(req, res, next));
app.delete('/tabelas/:id', (req, res, next) => nodeProxy(req, res, next));
app.get('/tabelas/:id', (req, res, next) => nodeProxy(req, res, next));
app.get('/tabelas', (req, res, next) => nodeProxy(req, res, next));


// Fretes
app.get('/custos/frete', (req, res, next) => nodeProxy(req, res, next));


// Entregas
app.get('/entregas/:id', (req, res, next) => javaProxy(req, res, next));
app.post('/entregas', (req, res, next) => javaProxy(req, res, next));
app.get('/entregas/entregador/:id', (req, res, next) => javaProxy(req, res, next));
app.pacth('/entregas/:id', (req, res, next) => javaProxy(req, res, next));


app.listen(port, () => console.log(`Example app listening on port ${port}!`));