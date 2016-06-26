function Validar() {

    if (document.cad.nome.value === "") {
        alert("Digite seu Nome!!");
        document.cad.nome.focus();
        return false;
    }
    if (document.cad.endereco.value === "") {
        alert("Digite seu Endereço!!");
        document.cad.endereco.focus();
        return false;
    }
    if (document.cad.telefone.value === "") {
        alert("Digite seu Telefone!!");
        document.cad.telefone.focus();
        return false;
    }
    if (document.cad.cpf.value === "") {
        alert("Digite seu CPF!!");
        document.cad.cpf.focus();
        return false;
    }
    if (document.cad.rg.value === "") {
        alert("Digite seu RG!!");
        document.cad.rg.focus();
        return false;
    }
    if (document.cad.rendaMensal.value === "") {
        alert("Digite sua Renda Mensal!!");
        document.cad.rendaMensal.focus();
        return false;
    }
    if (document.cad.email.value === "" || document.cad.email.value.indexOf("@") === -1 || document.cad.email.value.indexOf(".") === -1) {
        alert("Digite um E-mail valido!!");
        document.cad.email.focus();
        return false;
    }

    if (document.cad.radioSexo.checked === !false) {
        alert("Por Favor, selecione seu SEXO para continuar..");
        return false;
    }
   
    if (document.cad.radioTipoPessoa.checked === !false){
        alert("Por Favor, selecione uma opção para continuar...");
        return false;

    }
    if (document.cad.senha.value.length < 8 || document.cad.senha.value === "") {
        alert("A senha deve conter no mínimo 8 caracteres");
        document.cad.senha.focus();
        return false;
    }
    if (document.cad.senha2.value.length < 8 || document.cad.senha.value === "" || document.cad.senha !== document.cad.senha2) {
        alert("A senha NÃO confere com a primeira");
        document.cad.senha2.focus();
        return false;
    } else {
        alert("Cadastro Completo....");
    }

}
function Login() {

    if (document.log.numeroAgencia.value === "") {
        alert("Digite numero de sua AGENCIA!!");
        document.log.numeroAgencia.focus();
        return false;

    }
    if (document.log.numeroConta.value === "") {
        alert("Digite o numero de sua CONTA!!");
        document.log.numeroConta.focus();
        return false;
    }
    if (document.log.senha.value.length < 8 || document.log.senha.value === "") {
        alert("Digite corretamente sua senha, minimo 8 caracteres!!");
        document.log.senha.focus();
        return false;
    } else {
        alert("Login Completo....");
    }
}
function Emprestimo() {

    if (document.emp.valor.value === "") {
        alert("Informe o Valor do EMPRESTIMO");
        document.emp.valor.focus();
        return false;

    }
    if (document.emp.prazo.value === "") {
        alert("Informe o prazo do EMPRESTIMO !!");
        document.emp.prazo.focus();
        return false;
    } else {
        alert("AGUARDE....");
    }
}

function Financiamento() {

    if (document.fin.valor.value === "") {
        alert("Informe o Valor do FINANCIAMENTO");
        document.fin.valor.focus();
        return false;

    }
    if (document.fin.valorEntrada.value === "") {
        alert("Informe o Valor da entrada do FINANCIAMENTO");
        document.fin.valor.focus();
        return false;

    }
    if (document.fin.prazo.value === "") {
        alert("Informe o prazo do FINANCIAMENTO!!");
        document.fin.prazo.focus();
        return false;
    } else {
        alert("AGUARDE....");
    }
}
function Saque() {

    if (document.saq.valor.value === "") {
        alert("Informe seu SAQUE!!");
        document.emp.valor.focus();
        return false;
    } else {
        alert("AGUARDE....");
    }
}

function Deposito() {

    if (document.dep.valor.value === "") {
        alert("Informe seu SAQUE!!");
        document.dep.valor.focus();
        return false;
    } else {
        alert("AGUARDE....");
    }
}

//------------------------------------------------------------------------------//

function validarCPF(cpf) {
    var filtro = /^\d{3}.\d{3}.\d{3}-\d{2}$/i;

    if (!filtro.test(cpf))
    {
        window.alert("CPF inválido. Tente novamente.");
        return false;
    }

    cpf = remove(cpf, ".");
    cpf = remove(cpf, "-");

    if (cpf.length !== 11 || cpf === "00000000000" || cpf === "11111111111" ||
            cpf === "22222222222" || cpf === "33333333333" || cpf === "44444444444" ||
            cpf === "55555555555" || cpf === "66666666666" || cpf === "77777777777" ||
            cpf === "88888888888" || cpf === "99999999999")
    {
        window.alert("CPF inválido. Tente novamente.");
        return false;
    }

    soma = 0;
    for (i = 0; i < 9; i++)
    {
        soma += parseInt(cpf.charAt(i)) * (10 - i);
    }

    resto = 11 - (soma % 11);
    if (resto === 10 || resto === 11)
    {
        resto = 0;
    }
    if (resto !== parseInt(cpf.charAt(9))) {
        window.alert("CPF inválido. Tente novamente.");
        return false;
    }

    soma = 0;
    for (i = 0; i < 10; i++)
    {
        soma += parseInt(cpf.charAt(i)) * (11 - i);
    }
    resto = 11 - (soma % 11);
    if (resto === 10 || resto === 11)
    {
        resto = 0;
    }

    if (resto !== parseInt(cpf.charAt(10))) {
        window.alert("CPF inválido. Tente novamente.");
        return false;
    }

    return true;
}

function remove(str, sub) {
    i = str.indexOf(sub);
    r = "";
    if (i === -1)
        return str;
    {
        r += str.substring(0, i) + remove(str.substring(i + sub.length), sub);
    }

    return r;
}
function mascara(o, f) {
    v_obj = o;
    v_fun = f;
    setTimeout("execmascara()", 1);
}

function execmascara() {
    v_obj.value = v_fun(v_obj.value);
}

function cpf_mask(v) {
    v = v.replace(/\D/g, "");                 //Remove tudo o que não é dígito
    v = v.replace(/(\d{3})(\d)/, "$1.$2");    //Coloca ponto entre o terceiro e o quarto dígitos
    v = v.replace(/(\d{3})(\d)/, "$1.$2");    //Coloca ponto entre o setimo e o oitava dígitos
    v = v.replace(/(\d{3})(\d)/, "$1-$2");   //Coloca ponto entre o decimoprimeiro e o decimosegundo dígitos
    return v;
}

//------------------------------------------------------------------------------//
function mtel(v) {
    v = v.replace(/\D/g, "");             //Remove tudo o que não é dígito
    v = v.replace(/^(\d{2})(\d)/g, "($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
    v = v.replace(/(\d)(\d{4})$/, "$1-$2");    //Coloca hífen entre o quarto e o quinto dígitos
    return v;
}
//------------------------------------------------------------------------------//
function SomenteNumero(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if ((tecla > 47 && tecla < 58))
        return true;
    else {
        if (tecla === 8 || tecla === 0)
            return true;
        else
            return false;
    }
}
//------------------------------------------------------------------------------//

function MascaraMoeda(objTextBox, SeparadorMilesimo, SeparadorDecimal, e) {
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    if (whichCode === 13)
        return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave
    if (strCheck.indexOf(key) === -1)
        return false; // Chave inválida
    len = objTextBox.value.length;
    for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) !== '0') && (objTextBox.value.charAt(i) !== SeparadorDecimal))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) !== -1)
            aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len === 0)
        objTextBox.value = '';
    if (len === 1)
        objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
    if (len === 2)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j === 3) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
    }
    return false;
}
