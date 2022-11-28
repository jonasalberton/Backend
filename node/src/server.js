const { userRouter } = require("./routes/user-router");
const { freightRouter  } = require("./routes/freight-router");
const express = require("express");

const PORT = 3336;
const DB = 'mongodb://root:example@192.168.16.100:27017/?authMechanism=DEFAULT';

const server = express();

server.use(express.json());
server.use("/user", userRouter);
server.use("/custo", freightRouter);

async function main() {
  // connect to db
  server.listen(PORT, () => console.log("Server running at: ", PORT));
}

main().catch((err) => console.log(err));