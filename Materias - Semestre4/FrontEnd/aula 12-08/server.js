const http = require('http');

http.createServer((req,res)=>{
    res.write(`Olá Mundo`)
    res.end()
}).listen(3000, ()=>{
    console.log(`Servidor on!`)
})
console.log("Servidor rodando na porta 3000")
