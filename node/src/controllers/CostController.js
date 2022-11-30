const { validationResult } = require('express-validator');
const { getLocationByCep, getDistanceMatrix } = require('../services/googleService');

const calcFreightCost = async (req, res, next) => {
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    return res.status(400).json({ errors: errors.array() });
  }

  const { origem, destino, tabela } = req.query

  try {
    // pega locations
    const origemLocation = await getLocationByCep(origem)
    const destinoLocation = await getLocationByCep(destino)
    

    if (origemLocation.status !== 'OK' && destinoLocation.status !== 'OK') {
      throw 'Something wrong with Google Integration, check informations you provided!'
    }

    // pega distancia
    const originPoint = `${origemLocation.results[0].geometry.location.lat},${origemLocation.results[0].geometry.location.lng}`
    const destinationPoint = `${destinoLocation.results[0].geometry.location.lat},${destinoLocation.results[0].geometry.location.lng}`
    const result = await getDistanceMatrix(originPoint, destinationPoint)
    const distance = result.rows[0].elements[0].distance


    // buscar tabela frete
    // se nao existir throw error


    const distanciaEmMetros = distance.value
    const distanciaKm = Math.round(distance.value / 1000)
    const valorKm = .55
    const taxa = 12
    const valorFrete = (distanciaKm * valorKm) + taxa
    const origemData = origemLocation.results[0].address_components
    const destinoData = destinoLocation.results[0].address_components


    const response = {
      origem: {
        cep: origemData.find((el) => el.types.includes('postal_code'))?.long_name || '',
        logradouro: origemData.find((el) => el.types.includes('route'))?.long_name || '',
        bairro: origemData.find((el) => el.types.includes('sublocality'))?.long_name || '',
        cidade: origemData.find((el) => el.types.includes('administrative_area_level_2'))?.long_name || '',
        uf: origemData.find((el) => el.types.includes('administrative_area_level_1'))?.short_name || '',
        pais: origemData.find((el) => el.types.includes('country'))?.long_name || ''
      },
      destino: {
        cep: destinoData.find((el) => el.types.includes('postal_code'))?.long_name || '',
        logradouro: destinoData.find((el) => el.types.includes('route'))?.long_name || '',
        bairro: destinoData.find((el) => el.types.includes('sublocality'))?.long_name || '',
        cidade: destinoData.find((el) => el.types.includes('administrative_area_level_2'))?.long_name || '',
        uf: destinoData.find((el) => el.types.includes('administrative_area_level_1'))?.short_name || '',
        pais: origemData.find((el) => el.types.includes('country'))?.long_name || ''
      },
      distancia: distanciaKm,
      valorKm: valorKm,
      taxa: taxa,
      valor: valorFrete,
    }

    return res.status(200).json(response)
  } catch (error) {
    return res.status(500).json({ error: error });
  }
};


module.exports.calcFreightCost = calcFreightCost;