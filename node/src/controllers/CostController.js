const { validationResult } = require('express-validator');
const { client } = require('../db/client');
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

    const text = `SELECT * FROM tabela_frete WHERE id = $1`
    const values = [tabela]

    // busca tabela de frete do banco
    let tabela_frete
    try {
      const res = await client.query(text, values)
      tabela_frete = res.rows[0]
      console.log(res.rows[0])
    } catch (err) {
      throw err
    }

    const distanciaEmMetros = distance.value
    const distanciaKm = Math.round(distance.value / 1000)
    const valorKm = parseFloat(tabela_frete.km_percorrido)
    const taxa = parseFloat(tabela_frete.taxa_administracao)
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


const getEntregaById = async (req, res, next) => {
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    return res.status(400).json({ errors: errors.array() });
  }

  const { id } = req.params
 
  try {
    if (parseInt(id) <= 0) {
      throw 'Parametro id deve ser maior que 0'
    }
    
     // get entrega by id
    let entrega
    try {
      const text = `SELECT * FROM entrega WHERE id = $1`
      const values = [id]

      const res = await client.query(text, values)
      if (res.rows.length === 0 ) throw 'Entregador nao encontrada'
      entrega = res.rows[0]
    } catch (err) {
      throw err
    }

    const origem = parseInt(entrega.cep_origem_entrega)
    const destino = parseInt(entrega.cep_destino_entrega)

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


    const text = `SELECT * FROM tabela_frete WHERE id = $1`
    const values = [entrega.id_tabela_frete]

    // busca tabela de frete do banco
    let tabela_frete
    try {
      const res = await client.query(text, values)
      if (res.rows.length === 0 ) throw 'Tabela nao encontrada'
      tabela_frete = res.rows[0]
    } catch (err) {
      throw err
    }


    const distanciaKm = Math.round(distance.value / 1000)
    const valorKm = parseFloat(tabela_frete.km_percorrido)
    const taxa = parseFloat(tabela_frete.taxa_administracao)
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
      status: entrega.status,
      descricao: entrega.descricao_carga,
      id: entrega.id
    }


    return res.status(200).json(response)
  } catch (error) {
    return res.status(500).json({ error: error });
  }
}



const getEntregaByEntregadorId = async (req, res, next) => {
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    return res.status(400).json({ errors: errors.array() });
  }

  const { id } = req.params
 
  try {
    if (parseInt(id) <= 0) {
      throw 'Parametro id deve ser maior que 0'
    }
    
     // get entrega by id
    let entregas
    try {
      const text = `SELECT * FROM entrega WHERE id_entregador = $1`
      const values = [id]

      const res = await client.query(text, values)
      if (res.rows.length === 0 ) throw 'Nenhum entregador foi encontrado para esse entregador'
      entregas = res.rows
    } catch (err) {
      throw err
    }

    let entregaReponse = []

    for (const entrega of entregas) {
      const origem = parseInt(entrega.cep_origem_entrega)
      const destino = parseInt(entrega.cep_destino_entrega)
  
      // // pega locations
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
  
  
      const text = `SELECT * FROM tabela_frete WHERE id = $1`
      const values = [entrega.id_tabela_frete]
  
      // busca tabela de frete do banco
      let tabela_frete
      try {
        const res = await client.query(text, values)
        if (res.rows.length === 0 ) throw 'Tabela nao encontrada'
        tabela_frete = res.rows[0]
      } catch (err) {
        throw err
      }
  
  
      const distanciaKm = Math.round(distance.value / 1000)
      const valorKm = parseFloat(tabela_frete.km_percorrido)
      const taxa = parseFloat(tabela_frete.taxa_administracao)
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
        status: entrega.status,
        descricao: entrega.descricao_carga,
        id: entrega.id
      }

      entregaReponse.push(response)
    }

    return res.status(200).json({ entregas: entregaReponse})
  } catch (error) {
    return res.status(500).json({ error: error });
  }
}

module.exports = { calcFreightCost, getEntregaById, getEntregaByEntregadorId }