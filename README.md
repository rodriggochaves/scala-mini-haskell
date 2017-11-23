# MiniHaskell

```
Exp:: Int
	| Bool
	| Soma Exp Exp
	| And Exp Exp
	| Lambda(x) -> Exp
	| AppLambda(Exp, Exp)
	| Let x Exp Exp
```

## Projeto

1. Expressões pendentes
2. Suporte de multiplos parâmetros com seus respectivos tipos
3. Declaração de Funções Nomeadas
4. Implementar Expressão `If Then Else`
5. Corrigir os erros de compilação dos testes disponibilizados.

## Sugestão

```
Class Gama {
	HashMap ( variavel => tipo )
}
```

## TO DO

**Expressões pendentes**
+ AND
+ OR
+ NOT
+ MULTIPLICAÇAO
+ SUBTRACAO
+ DIVISÃO

**Parâmetro**
Uma classe que tem:
+ nome
+ tipo

**Declaração de Função**
Uma classe que tem:
+ nome: String
+ **lista de parâmetros**: Lista de Símbolos
+ corpo: Expressão

**Aplicação de Função Nomeada**
```
	| AppFuncaoNomeada(nome, Expressao)
```

**Verificação de Tipos**

**Tentar melhorar o Código**
+ implementação de Stack mais atualizada
+ nome dos funções