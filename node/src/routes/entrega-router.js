const router = require("express").Router();
const { query } = require("express-validator");
const { calcFreightCost, getEntregaById, getEntregaByEntregadorId } = require("../controllers/CostController");

router.get("/custos/frete",
  query("origem")
    .isLength({ min: 8, max: 8})
    .withMessage('tamanho 8 caracteres'),

    query("destino")
    .isLength({ min: 8, max: 8})
    .withMessage('tamanho 8 caracteres'),

    query("tabela").isNumeric(),
  calcFreightCost
);

router.get("/:id", getEntregaById);

router.get('/entregador/:id', getEntregaByEntregadorId )

module.exports.entregaRouter = router;
