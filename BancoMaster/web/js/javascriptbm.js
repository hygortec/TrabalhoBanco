function validar(nome, endereco, telefone, cpf, rg, rendaMensal, email, senha, senha2, sexo, tipoPessoa) {

        if (nome.value === "") {
        alert("Digite seu Nome!!");
                nome.focus();
                return false;
        }
        if (endereco.value === "") {
        alert("Digite seu Endereço!!");
                endereco.focus();
                return false;
        }
        if (cpf.value === "") {
        alert("Digite seu CPF!!");
                cpf.focus();
                return false;
        }
        if (rg.value === "") {
        alert("Digite seu RG!!");
                rg.focus();
                return false;
        }
        if (rendaMensal.value === "") {
        alert("Digite sua Renda Mensal!!");
                rendaMensal.focus();
                return false;
        }
        if (email.value === "" || email.value.indexOf("@") === -1 || email.value.indexOf(".") === -1) {
        alert("Digite um E-mail valido!!");
                email.focus();
                return false;
        }
        if (senha.value.length < 8 || senha.value === "") {
        alert("A senha deve conter no mínimo 8 caracteres");
                senha.focus();
                return false;
        }
        if (senha2.value.length < 8 || senha.value === "" || !senha.value) {
        alert("A senha NÃO confere com a primeira");
                senha2.focus();
                return false;
        }
        var masculino = document.getElementsBysexo("1").checked;
        var feminino = document.getElementsBysexo("2").checked;
         if ((masculino === "")&& (feminino === "")) {
        alert("Por Favor selecione o SEXO!!");
                return false;
        }
         if (tipoPessoa) {
        alert("Por Favor repita sua nova senha!!");
                tipoPessoa.focus();
                return false;
        }else{
        alert("Cadastro Completo....");
        }

        }