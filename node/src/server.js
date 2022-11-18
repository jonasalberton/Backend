const express = require("express");

const { userRouter } = require("./routes/user-router");

// Constants
const PORT = 3336;
const DB = 'mongodb://root:example@192.168.16.100:27017/?authMechanism=DEFAULT';

const server = express();

server.use(express.json());
server.use("/api/user", userRouter);

async function main() {
//   await mongoose.connect(DB);
  server.listen(PORT, () => console.log("Server running!"));
}

main().catch((err) => console.log(err));