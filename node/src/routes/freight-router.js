const router = require("express").Router();
const { body } = require("express-validator");
const { calcFreightCost } = require("../controllers/CostController");

router.get("/frete",
  body("origem").isNumeric(),
  body("destino").isNumeric(),
  body("tabela").isNumeric(),
  calcFreightCost
);

module.exports.freightRouter = router;
