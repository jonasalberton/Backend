const express = require("express");
const httpProxy = require("express-http-proxy");
const axios = require("axios")
const app = express();
const port = 3000;

const NODE_API = "http://localhost:3336";
const JAVA_API = "http://localhost:8080";

const nodeProxy = httpProxy(NODE_API);
const javaProxy = httpProxy(JAVA_API);

async function authMiddleware(req, res, next) {
  try {
    result = await axios.get(`${JAVA_API}/tabelas/2`, { headers: req.headers })
    next();
  } catch (error) {
    return res.status(401).json({ error: 'Not authorized' });
  }
}

app.use(authMiddleware);

app.get("/", (req, res) => res.send("Hello Gateway API"));

// Tabelas
app.post("/tabelas", (req, res, next) => javaProxy(req, res, next));
app.put("/tabelas/:id", (req, res, next) => javaProxy(req, res, next));
app.delete("/tabelas/:id", (req, res, next) => javaProxy(req, res, next));
app.get("/tabelas/:id", (req, res, next) => javaProxy(req, res, next));
app.get("/tabelas", (req, res, next) => javaProxy(req, res, next));
app.post("/entregas", (req, res, next) => javaProxy(req, res, next));
app.patch("/entregas/:id", (req, res, next) => javaProxy(req, res, next));


// Entregas
app.get("/entregas/custos/frete", (req, res, next) => nodeProxy(req, res, next));
app.get("/entregas/:id", (req, res, next) => nodeProxy(req, res, next));
app.get("/entregas/entregador/:id", (req, res, next) =>
  nodeProxy(req, res, next)
);

app.listen(port, () => console.log(`Example app listening on port ${port}!`));
