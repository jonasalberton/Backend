const { entregaRouter  } = require("./routes/entrega-router");
const express = require("express");
const server = express();
require('./db/client')

const PORT = 3336

server.use(express.json());
server.use("/entregas", entregaRouter);

async function main() {
  server.listen(PORT, () => console.log("Server running at: ", PORT));
}

main().catch((err) => console.log(err));