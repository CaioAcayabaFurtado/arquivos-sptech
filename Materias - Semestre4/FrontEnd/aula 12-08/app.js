const filtrarNumeros = (lista, valor) => {
    return lista.filter(num => num > valor);
}
// console.log(filtrarNumeros([10, 20, 30, 40, 50], 25));

function filtrarNomes (lista, letra) {
    return lista.filter(nome => nome.startsWith(letra));
}
// console.log(filtrarNomes([`Ana`, `João`, `Maria`], `A`));

// /////////////////////////////////////////// Prática de Node JS /////////////////////////////////////////// //

// Dica: use os métodos para vetor, como .map(), .filter(), .find(), .splice() para facilitar a manipulação e validação das listas.

// .map()
// Cria um novo array com o resultado da execução de uma função em cada elemento do array original.
// Exemplo: [1,2,3].map(x => x * 2) retorna [2,4,6].

// .filter()
// Cria um novo array apenas com os elementos que passaram em um teste (função de filtro).
// Exemplo: [1,2,3,4].filter(x => x % 2 === 0) retorna [2,4].

// .find()
// Retorna o primeiro elemento do array que satisfaz a condição da função de teste.
// Exemplo: [1,2,3,4].find(x => x > 2) retorna 3.

// .splice()
// Permite adicionar, remover ou substituir elementos do array original.
// Exemplo:
// Se quiser exemplos práticos ou mais detalhes, só pedir!

// 1. Crie uma função que receba um vetor de números e retorne o primeiro elemento.
function retornarPrimeiro(lista){
    return lista[0];
}
console.log(retornarPrimeiro([1, 2, 3, 4, 5]));

// 2. Crie uma função que receba um vetor de números e retorne uma nova lista com os 3 últimos elementos. 
function retortarTresUltimos(lista){
    return lista.slice(-3);
}
console.log(retortarTresUltimos([1, 2, 3, 4, 5]));

// 3. Crie uma função que receba um vetor de números e retorne a quantidade de elementos pares no vetor.
function contarPares(lista) {
    return lista.filter(num => num % 2 == 0).length;
}
console.log(contarPares([1, 2, 3, 4, 5, 6]));

// 4. Crie uma função que receba um vetor de números e retorne a quantidade de elementos ímpares no vetor.
function contarImpares(lista) {
    return lista.filter(num => num % 2 != 0).length;
}
console.log(contarImpares([1, 2, 3, 4, 5, 6]));

// 5. Crie uma função que receba um vetor de números e retorne a quantidade de números negativos presentes no vetor.
function contarNegativos(lista) {
    return lista.filter(num => num < 0).length;
}
console.log(contarNegativos([-1, 2, -3, 4, -5]));

// 6. Crie uma função que receba um vetor de números e retorne a soma de todos os elementos do vetor.
function somarElementos(lista) {
    total = 0;
    for (num of lista){
        total += num;
    }
    return total
}
console.log(somarElementos([1, 2, 3, 4, 5]));

// 7. Crie uma função que receba um vetor de números e retorne o maior valor presente no vetor.
function encontrarMaior(lista) {
    maxNum = lista[0];
    for (num of lista){
        if (num > maxNum){
            maxNum = num;
        }
    }
    return maxNum;
}
console.log(encontrarMaior([1, 2, 3, 4, 5]));

// 8. Crie uma função que receba um vetor de números e retorne o menor valor presente no vetor.
function encontrarMenor(lista) {
    minNum = lista[0];
    for (num of lista){
        if (num < minNum){
            minNum = num;
        }
    }
    return minNum;
}
console.log(encontrarMenor([1, 2, 3, 4, 5]));

// 9. Crie uma função que receba um vetor de números e retorne true se todos os elementos forem positivos, ou false caso contrário.
function todosPositivos(lista) {
    for (num of lista){
        if (num < 0){
            return false;
        }
    }
    return true;
}
console.log(todosPositivos([1, 2, 3, 4, 5]));
console.log(todosPositivos([-1, 2, 3, 4, 5]));

// 10. Crie uma função que receba um vetor de números e um valor, e retorne a quantidade de vezes que esse valor aparece no vetor.
function contarOcorrencias(lista, valor) {
    return lista.filter(num => num == valor).length;
}
console.log(contarOcorrencias([1, 2, 3, 4, 5, 2], 2));

// 11. Crie uma função que receba um vetor de números e retorne um novo vetor contendo apenas os números pares do vetor original.
function filtrarPares(lista) {
    return lista.filter(num => num % 2 == 0);
}
console.log(filtrarPares([1, 2, 3, 4, 5, 6]));

// 12. Crie uma função que receba um vetor de números e retorne um novo vetor contendo apenas os números ímpares do vetor original.
function filtrarImpares(lista) {
    return lista.filter(num => num % 2 != 0);
}
console.log(filtrarImpares([1, 2, 3, 4, 5, 6]));

// 13. Crie uma função que receba um vetor de números e retorne um novo vetor com os elementos em ordem inversa.
function inverterLista(lista) {
    return lista.reverse();
}
console.log(inverterLista([1, 2, 3, 4, 5]));

// 14. Crie uma função que receba um vetor de números e retorne true se houver elementos duplicados no vetor, ou false caso contrário.
function temDuplicatas(lista) {
    const set = new Set(lista);
    return set.size !== lista.length;
}
console.log(temDuplicatas([1, 2, 3, 4, 5]));
console.log(temDuplicatas([1, 2, 3, 4, 5, 2]));

// 15. Crie uma função que receba um vetor de números e retorne a média dos valores.
function calcularMedia(lista) {
    if (lista.length === 0) return 0;
    const total = somarElementos(lista);
    return total / lista.length;
}
console.log(calcularMedia([1, 2, 3, 4, 5]));

// 16. Crie uma função que receba um vetor de números e um número n, e retorne um novo vetor contendo os valores da lista original que são maiores que n.
function filtrarMaiorQue(lista, n) { 
    return lista.filter(num => num > n);
}
console.log(filtrarMaiorQue([1, 2, 3, 4, 5], 3));

// 17. Crie uma função que receba um vetor de números e retorne apenas os valores únicos (sem duplicatas).
function filtrarUnicos(lista){
    return lista.filter((item, idx, arr) => arr.indexOf(item) === arr.lastIndexOf(item));
}
console.log(filtrarUnicos([1,2,2,3,4,5,5]))

// 18. Crie uma função que receba um vetor de números e retorne true se o vetor estiver ordenado em ordem crescente, ou false caso contrário. 
// Extra: valide se todos os elementos são números, caso contrário retorne "Lista Inválida".
function estaOrdenado(lista) {
    for (let i = 0; i < lista.length - 1; i++) {
        if (lista[i] > lista[i + 1]) {
            return false;
        }
    }
    return true;
}
console.log(estaOrdenado([1, 2, 3, 4, 5])); // true
console.log(estaOrdenado([5, 4, 3, 2, 1])); // false

// 19. Crie uma função que receba dois vetores e retorne um novo vetor com a soma dos elementos de mesma posição, ou "Vetores de tamanhos diferentes".
function somarVetores(vetor1, vetor2) {
    if (vetor1.length !== vetor2.length) {
        return "Vetores de tamanhos diferentes";
    }
    return vetor1.map((num, idx) => num + vetor2[idx]);
}
console.log(somarVetores([1, 2, 3], [4, 5, 6])); // [5, 7, 9]
console.log(somarVetores([1, 2], [3, 4, 5])); // "Vetores de tamanhos diferentes"
