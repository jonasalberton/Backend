const router = require("express").Router();
const { query, check } = require("express-validator");
const { calcFreightCost } = require("../controllers/CostController");

router.get("/frete",
  query("origem")
    .isNumeric()
    .withMessage('Deve ser numerico')
    .isLength({ min: 8, max: 8})
    .withMessage('tamanho 8 caracteres'),

    query("destino")
    .isNumeric()
    .withMessage('Deve ser numerico')
    .isLength({ min: 8, max: 8})
    .withMessage('tamanho 8 caracteres'),

    query("tabela").isNumeric(),
  calcFreightCost
);

module.exports.freightRouter = router;
