$(document).ready(function () {
    $(".nome-cliente").animate({opacity: 1,
        "margin-left": "+=5"
    }, 500);

    jQuery(function ($) {
        $("[class=inputTelefone]").mask("(99)99999-9999");
        $("[class=inputTelefoneFixo]").mask("(99)9999-9999");
        $("[class=input-cpf]").mask("999.999.999-99");
        $(".input-cpf").mask("999.999.999-99");
        $(".input-cnpj").mask("99.999.999/9999-99");
        $(".input-cep").mask("99999-999");
        $("[class=inputNumero]").mask("999999");
        $(".valor").mask("999.99");
    });

        $(".input-cpf").keyup(function (){
            valor = this.value.replace(".", "").replace("-", "").replace("_", "").replace(".", "");
            tamanho = valor.length;
                        
            if( tamanho === 11){
                var valido = (TestaCPF(valor));
                if(!valido){
                    alert("CPF Informado não é valido!");
                }
            }
        });

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });


    $('#pesquisar').keyup(function () {
        var encontrou = false;
        var termo = $(this).val().toLowerCase();
        var tabelas = document.getElementsByClassName('tabela-os');
        for (i = 0; i <= tabelas.length; i++) {
            var tabela = tabelas[i];

            $($(tabela).find('tr')).each(function () {
                $(this).find('td').each(function () {
                    if ($(this).text().toLowerCase().indexOf(termo) > -1)
                        encontrou = true;
                });
                if (!encontrou)
                    $(this).fadeOut('fast');
                else
                    $(this).fadeIn('fast');
                encontrou = false;
            });
        }
    });

    $(".valorPagto").keyup(function () {
        if ($(".troco").val(($(".valorPagto").val()) - ($(".valorservico").val())) === NaN()) {
            $(".troco").val("");
            return;
        }
        if (($(".valorPagto").val()) > ($(".valorservico").val())) {
            $(".valorPagto").addClass("is-invalid");
            $(".troco").val(($(".valorPagto").val()) - ($(".valorservico").val()));
        } else {
            $(".valorPagto").removeClass("is-invalid");
            $(".troco").val(0);
        }
    });
    
    $(".input-valor").keypress(function (e) {
        return(moeda(this,'.',',',e));
    });

});

$(function () {
    $(".datepicker").datepicker();
});

function moeda(a, e, r, t) {
    let n = ""
      , h = j = 0
      , u = tamanho2 = 0
      , l = ajd2 = ""
      , o = window.Event ? t.which : t.keyCode;
    if (13 == o || 8 == o)
        return !0;
    if (n = String.fromCharCode(o),
    -1 == "0123456789".indexOf(n))
        return !1;
    for (u = a.value.length,
    h = 0; h < u && ("0" == a.value.charAt(h) || a.value.charAt(h) == r); h++)
        ;
    for (l = ""; h < u; h++)
        -1 != "0123456789".indexOf(a.value.charAt(h)) && (l += a.value.charAt(h));
    if (l += n,
    0 == (u = l.length) && (a.value = ""),
    1 == u && (a.value = "0" + r + "0" + l),
    2 == u && (a.value = "0" + r + l),
    u > 2) {
        for (ajd2 = "",
        j = 0,
        h = u - 3; h >= 0; h--)
            3 == j && (ajd2 += e,
            j = 0),
            ajd2 += l.charAt(h),
            j++;
        for (a.value = "",
        tamanho2 = ajd2.length,
        h = tamanho2 - 1; h >= 0; h--)
            a.value += ajd2.charAt(h);
        a.value += r + l.substr(u - 2, u)
    }
    return !1
}

function TestaCPF(strCPF) {
    var Soma;
    var Resto;
    Soma = 0;
    if (strCPF == "00000000000")
        return false;

    for (i = 1; i <= 9; i++)
        Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))
        Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10)))
        return false;

    Soma = 0;
    for (i = 1; i <= 10; i++)
        Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))
        Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11)))
        return false;
    return true;
}


/* Caiu em desuso pela máscara do campo */
function somenteNumeros(num) {
    var er = /[^0-9.,]/;
    er.lastIndex = 0;
    var campo = num;
    if (er.test(campo.value)) {
        campo.value = "";
        //campo.value = campo.value.substring(0, length(campo.value) - 1);
    }
}

!function (factory) {
    "function" == typeof define && define.amd ? define(["jquery"], factory) : factory("object" == typeof exports ? require("jquery") : jQuery);
}(function ($) {
    var caretTimeoutId, ua = navigator.userAgent, iPhone = /iphone/i.test(ua), chrome = /chrome/i.test(ua), android = /android/i.test(ua);
    $.mask = {
        definitions: {
            "9": "[0-9]",
            a: "[A-Za-z]",
            "*": "[A-Za-z0-9]"
        },
        autoclear: !0,
        dataName: "rawMaskFn",
        placeholder: "_"
    }, $.fn.extend({
        caret: function (begin, end) {
            var range;
            if (0 !== this.length && !this.is(":hidden"))
                return "number" == typeof begin ? (end = "number" == typeof end ? end : begin,
                        this.each(function () {
                            this.setSelectionRange ? this.setSelectionRange(begin, end) : this.createTextRange && (range = this.createTextRange(),
                                    range.collapse(!0), range.moveEnd("character", end), range.moveStart("character", begin),
                                    range.select());
                        })) : (this[0].setSelectionRange ? (begin = this[0].selectionStart, end = this[0].selectionEnd) : document.selection && document.selection.createRange && (range = document.selection.createRange(),
                        begin = 0 - range.duplicate().moveStart("character", -1e5), end = begin + range.text.length),
                        {
                            begin: begin,
                            end: end
                        });
        },
        unmask: function () {
            return this.trigger("unmask");
        },
        mask: function (mask, settings) {
            var input, defs, tests, partialPosition, firstNonMaskPos, lastRequiredNonMaskPos, len, oldVal;
            if (!mask && this.length > 0) {
                input = $(this[0]);
                var fn = input.data($.mask.dataName);
                return fn ? fn() : void 0;
            }
            return settings = $.extend({
                autoclear: $.mask.autoclear,
                placeholder: $.mask.placeholder,
                completed: null
            }, settings), defs = $.mask.definitions, tests = [], partialPosition = len = mask.length,
                    firstNonMaskPos = null, $.each(mask.split(""), function (i, c) {
                        "?" == c ? (len--, partialPosition = i) : defs[c] ? (tests.push(new RegExp(defs[c])),
                                null === firstNonMaskPos && (firstNonMaskPos = tests.length - 1), partialPosition > i && (lastRequiredNonMaskPos = tests.length - 1)) : tests.push(null);
                    }), this.trigger("unmask").each(function () {
                function tryFireCompleted() {
                    if (settings.completed) {
                        for (var i = firstNonMaskPos; lastRequiredNonMaskPos >= i; i++)
                            if (tests[i] && buffer[i] === getPlaceholder(i))
                                return;
                        settings.completed.call(input);
                    }
                }
                function getPlaceholder(i) {
                    return settings.placeholder.charAt(i < settings.placeholder.length ? i : 0);
                }
                function seekNext(pos) {
                    for (; ++pos < len && !tests[pos]; )
                        ;
                    return pos;
                }
                function seekPrev(pos) {
                    for (; --pos >= 0 && !tests[pos]; )
                        ;
                    return pos;
                }
                function shiftL(begin, end) {
                    var i, j;
                    if (!(0 > begin)) {
                        for (i = begin, j = seekNext(end); len > i; i++)
                            if (tests[i]) {
                                if (!(len > j && tests[i].test(buffer[j])))
                                    break;
                                buffer[i] = buffer[j], buffer[j] = getPlaceholder(j), j = seekNext(j);
                            }
                        writeBuffer(), input.caret(Math.max(firstNonMaskPos, begin));
                    }
                }
                function shiftR(pos) {
                    var i, c, j, t;
                    for (i = pos, c = getPlaceholder(pos); len > i; i++)
                        if (tests[i]) {
                            if (j = seekNext(i), t = buffer[i], buffer[i] = c, !(len > j && tests[j].test(t)))
                                break;
                            c = t;
                        }
                }
                function androidInputEvent() {
                    var curVal = input.val(), pos = input.caret();
                    if (oldVal && oldVal.length && oldVal.length > curVal.length) {
                        for (checkVal(!0); pos.begin > 0 && !tests[pos.begin - 1]; )
                            pos.begin--;
                        if (0 === pos.begin)
                            for (; pos.begin < firstNonMaskPos && !tests[pos.begin]; )
                                pos.begin++;
                        input.caret(pos.begin, pos.begin);
                    } else {
                        for (checkVal(!0); pos.begin < len && !tests[pos.begin]; )
                            pos.begin++;
                        input.caret(pos.begin, pos.begin);
                    }
                    tryFireCompleted();
                }
                function blurEvent() {
                    checkVal(), input.val() != focusText && input.change();
                }
                function keydownEvent(e) {
                    if (!input.prop("readonly")) {
                        var pos, begin, end, k = e.which || e.keyCode;
                        oldVal = input.val(), 8 === k || 46 === k || iPhone && 127 === k ? (pos = input.caret(),
                                begin = pos.begin, end = pos.end, end - begin === 0 && (begin = 46 !== k ? seekPrev(begin) : end = seekNext(begin - 1),
                                        end = 46 === k ? seekNext(end) : end), clearBuffer(begin, end), shiftL(begin, end - 1),
                                e.preventDefault()) : 13 === k ? blurEvent.call(this, e) : 27 === k && (input.val(focusText),
                                input.caret(0, checkVal()), e.preventDefault());
                    }
                }
                function keypressEvent(e) {
                    if (!input.prop("readonly")) {
                        var p, c, next, k = e.which || e.keyCode, pos = input.caret();
                        if (!(e.ctrlKey || e.altKey || e.metaKey || 32 > k) && k && 13 !== k) {
                            if (pos.end - pos.begin !== 0 && (clearBuffer(pos.begin, pos.end), shiftL(pos.begin, pos.end - 1)),
                                    p = seekNext(pos.begin - 1), len > p && (c = String.fromCharCode(k), tests[p].test(c))) {
                                if (shiftR(p), buffer[p] = c, writeBuffer(), next = seekNext(p), android) {
                                    var proxy = function () {
                                        $.proxy($.fn.caret, input, next)();
                                    };
                                    setTimeout(proxy, 0);
                                } else
                                    input.caret(next);
                                pos.begin <= lastRequiredNonMaskPos && tryFireCompleted();
                            }
                            e.preventDefault();
                        }
                    }
                }
                function clearBuffer(start, end) {
                    var i;
                    for (i = start; end > i && len > i; i++)
                        tests[i] && (buffer[i] = getPlaceholder(i));
                }
                function writeBuffer() {
                    input.val(buffer.join(""));
            }
            function checkVal(allow) {
                var i, c, pos, test = input.val(), lastMatch = -1;
                for (i = 0, pos = 0; len > i; i++)
                    if (tests[i]) {
                                        for (buffer[i] = getPlaceholder(i); pos++
< test.length; )
if (c = test.charAt(pos - 1),
tests[i].test(c)) {
                                                    buffer[i] = c, lastMatch = i;
                                            break;
}
if (pos > test.length) {
                                        clearBuffer(i + 1, len);
                                        break;
}
} else
buffer[i] === test.charAt(pos) && pos++, partialPosition > i && (lastMatch = i);
return allow ? writeBuffer() : partialPosition > lastMatch + 1 ? settings.autoclear || buffer.join("") === defaultBuffer ? (input.val() && input.val(""),
clearBuffer(0, len)) : writeBuffer() : (writeBuffer(), input.val(input.val().substring(0, lastMatch + 1))),
partialPosition ? i : firstNonMaskPos;
}
var input = $(this), buffer = $.map(mask.split(""), function(c, i) {
                                    return "?" != c ? defs[c] ? getPlaceholder(i) : c : void 0;
}), defaultBuffer = buffer.join(""), focusText = input.val();
input.data($.mask.dataName, function() {
                                    return $.map(buffer, function (c, i) {
                                                return tests[i] && c != getPlaceholder(i) ? c : null;
}).join("");
}), input.one("unmask", function() {
                                                input.off(".mask").removeData($.mask.dataName);
}).on("focus.mask", function() {
                                                        if (!input.prop("readonly")) {
                                                    clearTimeout(caretTimeoutId);
                                                    var pos;
                                                    focusText = input.val(), pos = checkVal(), caretTimeoutId = setTimeout(function () {
                                                            input.get(0) === document.activeElement && (writeBuffer(), pos == mask.replace("?", "").length ? input.caret(0, pos) : input.caret(pos));
}, 10);
}
}).on("blur.mask", blurEvent).on("keydown.mask", keydownEvent).on("keypress.mask", keypressEvent).on("input.mask paste.mask", function() {
                                                            input.prop("readonly") || setTimeout(function () {
                                                                var pos = checkVal(!0);
                                                                input.caret(pos), tryFireCompleted();
}, 0);
}), chrome && android && input.off("input.mask").on("input.mask", androidInputEvent),
checkVal();
});
}
});
});


