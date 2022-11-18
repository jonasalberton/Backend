const router = require('express').Router();
const { createUser } = require('../controllers/UserController');

router.get('/', createUser);

module.exports.userRouter = router;