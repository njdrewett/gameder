
const PORT = 8000

const express = require('express')

const app = express() 

app.get('/', (request, response) => {
    response.json('running ')
})


app.listen(PORT, () => console.log('Server running on PORT ' + PORT))

