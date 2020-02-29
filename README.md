# PigCoins
Vamos a construir un prototipo de nuestra criptomoneda **Pigcoin** que permite gestionar mediante un **blockchain** la transferencia de monedas entre el alumnado y profesorado propietarios/as de **wallets** (o monederos).

- La *cadena de bloques o blockchain* está compuesta por las transacciones que envían las wallet o monederos entre sí, una detrás de otra. Cada entrada en el blockchain es una **transacción**.
- Una **wallet es un monedero**. Se identifica por su par *clave privada y clave pública*. Pertenece al usuario/a que conoce su clave privada. La clave pública es la dirección de la wallet a la que se envían (o donde se reciben) los pigcoins.
- Cuando un usuario/a envía pigcoins, los envía a la dirección o clave pública de la wallet de destino. **Firma la transacción con su clave privada**.
- El **blockchain** debe verificar que una transacción es auténtica antes de añadirla a la cadena de bloques. Verifica la firma de la transacción mediante la clave pública de la wallet que envía los pigcoins.
- El blockchain debe *verificar que los pigcoins de la transacción no han sido gastados antes*, es decir, que no existen en el blockchain transacciones que utilicen los pigcoins que se pretenden usar ahora.
- Una transacción es como un **billete de pigcoins**. Para gastarlo, ese billete debe habértelo entregado alguien y no puedes haberlo gastado antes. Cuando envías pigcoins, utilizas la transacción en la que los recibiste y dicha transacción NO puede volverse a utilizar (**se destruye esa moneda**). Cada transacción sólo puede ser utilizada una vez. En el blockchain no puede haber dos transacciones que provengan de la misma transacción.
- Los pigcoins son transacciones **indivisibles**. Si quieres enviar 8 pgc (pigcoins) y sólo dispones de un billete /transacción recibida de 10 pgc, debes enviar dos transacciones: una de 8 pgc para el destinatario/a y 2 pgc para tí mismo/a. A esto se la llama change address.

## Qué utilizamos en este proyecto?

1. Maps (Hashmap)
2. Métodos de Maps (get, put, containsValue)
3. For / If Else
4. ArrayList / List.of
5. Interfaces
6. TDD (Casos Test)
7. Abstracción
8. Polimorfismo
9. Encapsulación
