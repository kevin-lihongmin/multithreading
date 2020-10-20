package com.kevin.multithreading.geektime.other.sync;

public class JavacJavap {

    /*Compiled from "Singleton.java"
    public class com.kevin.multithreading.geektime.other.Singleton {
        public static com.kevin.multithreading.geektime.other.Singleton getInstance();
        Code:
        0: getstatic     #1                  // Field singleton:Lcom/kevin/multithreading/geektime/other/Singleton;
        3: ifnonnull     37
        6: ldc           #2                  // class com/kevin/multithreading/geektime/other/Singleton
        8: dup
        9: astore_0
        10: monitorenter
        11: getstatic     #1                  // Field singleton:Lcom/kevin/multithreading/geektime/other/Singleton;
        14: ifnonnull     27
        17: new           #2                  // class com/kevin/multithreading/geektime/other/Singleton
        20: dup
        21: invokespecial #3                  // Method "<init>":()V
        24: putstatic     #1                  // Field singleton:Lcom/kevin/multithreading/geektime/other/Singleton;
        27: aload_0
        28: monitorexit
        29: goto          37
        32: astore_1
        33: aload_0
        34: monitorexit
        35: aload_1
        36: athrow
        37: getstatic     #1                  // Field singleton:Lcom/kevin/multithreading/geektime/other/Singleton;
        40: areturn
        Exception table:
        from    to  target type
        11    29    32   any
        32    35    32   any

        public static void main(java.lang.String[]);
        Code:
        0: invokestatic  #5                  // Method getInstance:()Lcom/kevin/multithreading/geektime/other/Singleton;
        3: astore_1
        4: getstatic     #6                  // Field java/lang/System.out:Ljava/io/PrintStream;
        7: aload_1
        8: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
        11: return
    }*/

    // 反汇编相关，如下：

    /*CompilerOracle: compileonly *Singleton.getInstance
    Loaded disassembler from hsdis-amd64.dll
    Decoding compiled method 0x00000000036ada50:
    Code:
    Argument 0 is unknown.RIP: 0x36adbe0 Code size: 0x00000448
            [Disassembling for mach='amd64']
            [Entry Point]
            [Verified Entry Point]
            [Constants]
            # {method} {0x000000001cad2a78} 'getInstance' '()Lcom/kevin/multithreading/geektime/other/Singleton;' in 'com/kevin/multithreading/geektime/other/Singleton'
            #           [sp+0x50]  (sp of caller)
            0x00000000036adbe0: mov     dword ptr [rsp+0ffffffffffffa000h],eax
  0x00000000036adbe7: push    rbp
  0x00000000036adbe8: sub     rsp,40h
  0x00000000036adbec: mov     rax,1cad2d50h     ;   {metadata(method data for {method} {0x000000001cad2a78} 'getInstance' '()Lcom/kevin/multithreading/geektime/other/Singleton;' in 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036adbf6: mov     edx,dword ptr [rax+64h]
            0x00000000036adbf9: add     edx,8h
  0x00000000036adbfc: mov     dword ptr [rax+64h],edx
  0x00000000036adbff: mov     rax,1cad2a70h     ;   {metadata({method} {0x000000001cad2a78} 'getInstance' '()Lcom/kevin/multithreading/geektime/other/Singleton;' in 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036adc09: and     edx,0h
  0x00000000036adc0c: cmp     edx,0h
  0x00000000036adc0f: je      36adeech
  0x00000000036adc15: mov     rax,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036adc1f: mov     eax,dword ptr [rax+60h]
            0x00000000036adc22: shl     rax,3h            ;*getstatic singleton
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@0 (line 36)

            0x00000000036adc26: cmp     rax,0h
  0x00000000036adc2a: mov     rax,1cad2d50h     ;   {metadata(method data for {method} {0x000000001cad2a78} 'getInstance' '()Lcom/kevin/multithreading/geektime/other/Singleton;' in 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036adc34: mov     rdx,90h
  0x00000000036adc3e: jne     36adc4eh
  0x00000000036adc44: mov     rdx,0a0h
  0x00000000036adc4e: mov     rsi,qword ptr [rax+rdx]
            0x00000000036adc52: lea     rsi,[rsi+1h]
            0x00000000036adc56: mov     qword ptr [rax+rdx],rsi
  0x00000000036adc5a: jne     36ade60h          ;*ifnonnull
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@3 (line 36)

            0x00000000036adc60: mov     rdx,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036adc6a: lea     rsi,[rsp+28h]
            0x00000000036adc6f: mov     qword ptr [rsi+8h],rdx
  0x00000000036adc73: mov     rax,qword ptr [rdx]  ; implicit exception: dispatches to 0x00000000036adf03
            0x00000000036adc76: mov     rdi,rax
  0x00000000036adc79: and     rdi,7h
  0x00000000036adc7d: cmp     rdi,5h
  0x00000000036adc81: jne     36add10h
  0x00000000036adc87: mov     edi,dword ptr [rdx+8h]
            0x00000000036adc8a: shl     rdi,3h
  0x00000000036adc8e: mov     rdi,qword ptr [rdi+0a8h]
            0x00000000036adc95: or      rdi,r15
  0x00000000036adc98: xor     rdi,rax
  0x00000000036adc9b: and     rdi,0ffffffffffffff87h
  0x00000000036adc9f: je      36add38h
  0x00000000036adca5: test    rdi,7h
  0x00000000036adcac: jne     36adcfdh
  0x00000000036adcb2: test    rdi,300h
  0x00000000036adcb9: jne     36adcdch
  0x00000000036adcbf: and     rax,37fh
  0x00000000036adcc6: mov     rdi,rax
  0x00000000036adcc9: or      rdi,r15
  0x00000000036adccc: lock cmpxchg qword ptr [rdx],rdi
  0x00000000036adcd1: jne     36adf08h
  0x00000000036adcd7: jmp     36add38h
  0x00000000036adcdc: mov     edi,dword ptr [rdx+8h]
            0x00000000036adcdf: shl     rdi,3h
  0x00000000036adce3: mov     rdi,qword ptr [rdi+0a8h]
            0x00000000036adcea: or      rdi,r15
  0x00000000036adced: lock cmpxchg qword ptr [rdx],rdi
  0x00000000036adcf2: jne     36adf08h
  0x00000000036adcf8: jmp     36add38h
  0x00000000036adcfd: mov     edi,dword ptr [rdx+8h]
            0x00000000036add00: shl     rdi,3h
  0x00000000036add04: mov     rdi,qword ptr [rdi+0a8h]
            0x00000000036add0b: lock cmpxchg qword ptr [rdx],rdi
  0x00000000036add10: mov     rax,qword ptr [rdx]
            0x00000000036add13: or      rax,1h
  0x00000000036add17: mov     qword ptr [rsi],rax
  0x00000000036add1a: lock cmpxchg qword ptr [rdx],rsi
  0x00000000036add1f: je      36add38h
  0x00000000036add25: sub     rax,rsp
  0x00000000036add28: and     rax,0fffffffffffff007h
  0x00000000036add2f: mov     qword ptr [rsi],rax
  0x00000000036add32: jne     36adf08h          ;*monitorenter
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@10 (line 37)

            0x00000000036add38: mov     rdx,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036add42: mov     edx,dword ptr [rdx+60h]
            0x00000000036add45: shl     rdx,3h            ;*getstatic singleton
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@11 (line 38)

            0x00000000036add49: cmp     rdx,0h
  0x00000000036add4d: mov     rdx,1cad2d50h     ;   {metadata(method data for {method} {0x000000001cad2a78} 'getInstance' '()Lcom/kevin/multithreading/geektime/other/Singleton;' in 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036add57: mov     rsi,0b0h
  0x00000000036add61: jne     36add71h
  0x00000000036add67: mov     rsi,0c0h
  0x00000000036add71: mov     rdi,qword ptr [rdx+rsi]
            0x00000000036add75: lea     rdi,[rdi+1h]
            0x00000000036add79: mov     qword ptr [rdx+rsi],rdi
  0x00000000036add7d: jne     36ade15h          ;*ifnonnull
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@14 (line 38)

            0x00000000036add83: mov     rdx,7c0060828h    ;   {metadata('com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036add8d: mov     rax,qword ptr [r15+60h]
            0x00000000036add91: lea     rdi,[rax+10h]
            0x00000000036add95: cmp     rdi,qword ptr [r15+70h]
            0x00000000036add99: jnbe    36adf1bh
  0x00000000036add9f: mov     qword ptr [r15+60h],rdi
  0x00000000036adda3: mov     rcx,qword ptr [rdx+0a8h]
            0x00000000036addaa: mov     qword ptr [rax],rcx
  0x00000000036addad: mov     rcx,rdx
  0x00000000036addb0: shr     rcx,3h
  0x00000000036addb4: mov     dword ptr [rax+8h],ecx
  0x00000000036addb7: xor     rcx,rcx
  0x00000000036addba: mov     dword ptr [rax+0ch],ecx
  0x00000000036addbd: xor     rcx,rcx           ;*new  ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@17 (line 39)

            0x00000000036addc0: mov     rdx,rax
  0x00000000036addc3: mov     rsi,1cad2d50h     ;   {metadata(method data for {method} {0x000000001cad2a78} 'getInstance' '()Lcom/kevin/multithreading/geektime/other/Singleton;' in 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036addcd: add     qword ptr [rsi+0d0h],1h
  0x00000000036addd5: mov     rdx,rax           ;*invokespecial <init>
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@21 (line 39)

            0x00000000036addd8: mov     qword ptr [rsp+20h],rax
  0x00000000036adddd: nop
  0x00000000036addde: nop
  0x00000000036adddf: call    35e5ee0h          ; OopMap{[32]=Oop [48]=Oop off=516}
    ;*invokespecial <init>
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@21 (line 39)
    ;   {optimized virtual_call}
  0x00000000036adde4: mov     rax,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036addee: mov     rsi,qword ptr [rsp+20h]
            0x00000000036addf3: mov     r10,rsi
  0x00000000036addf6: shr     r10,3h
  0x00000000036addfa: mov     dword ptr [rax+60h],r10d
  0x00000000036addfe: shr     rax,9h
  0x00000000036ade02: mov     rsi,0f360000h
  0x00000000036ade0c: mov     byte ptr [rax+rsi],0h
  0x00000000036ade10: lock add dword ptr [rsp],0h  ;*putstatic singleton
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@24 (line 39)

            0x00000000036ade15: mov     rax,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ade1f: lea     rax,[rsp+28h]
            0x00000000036ade24: mov     rdi,qword ptr [rax+8h]
            0x00000000036ade28: mov     rsi,qword ptr [rdi]
            0x00000000036ade2b: and     rsi,7h
  0x00000000036ade2f: cmp     rsi,5h
  0x00000000036ade33: je      36ade50h
  0x00000000036ade39: mov     rsi,qword ptr [rax]
            0x00000000036ade3c: test    rsi,rsi
  0x00000000036ade3f: je      36ade50h
  0x00000000036ade45: lock cmpxchg qword ptr [rdi],rsi
  0x00000000036ade4a: jne     36adf28h          ;*monitorexit
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@28 (line 41)

            0x00000000036ade50: mov     rax,1cad2d50h     ;   {metadata(method data for {method} {0x000000001cad2a78} 'getInstance' '()Lcom/kevin/multithreading/geektime/other/Singleton;' in 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ade5a: inc     dword ptr [rax+0e0h]  ;*goto
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@29 (line 41)

            0x00000000036ade60: mov     rax,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ade6a: mov     eax,dword ptr [rax+60h]
            0x00000000036ade6d: shl     rax,3h            ;*getstatic singleton
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@37 (line 43)

            0x00000000036ade71: add     rsp,40h
  0x00000000036ade75: pop     rbp
  0x00000000036ade76: test    dword ptr [2600100h],eax
    ;   {poll_return}
  0x00000000036ade7c: ret                       ;*areturn
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@40 (line 43)

            0x00000000036ade7d: mov     rax,qword ptr [r15+288h]
            0x00000000036ade84: mov     r10,0h
  0x00000000036ade8e: mov     qword ptr [r15+288h],r10
  0x00000000036ade95: mov     r10,0h
  0x00000000036ade9f: mov     qword ptr [r15+290h],r10
  0x00000000036adea6: mov     rsi,rax
  0x00000000036adea9: mov     rax,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036adeb3: lea     rax,[rsp+28h]
            0x00000000036adeb8: mov     rbx,qword ptr [rax+8h]
            0x00000000036adebc: mov     rdi,qword ptr [rbx]
            0x00000000036adebf: and     rdi,7h
  0x00000000036adec3: cmp     rdi,5h
  0x00000000036adec7: je      36adee4h
  0x00000000036adecd: mov     rdi,qword ptr [rax]
            0x00000000036aded0: test    rdi,rdi
  0x00000000036aded3: je      36adee4h
  0x00000000036aded9: lock cmpxchg qword ptr [rbx],rdi
  0x00000000036adede: jne     36adf3bh          ;*monitorexit
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@34 (line 41)

            0x00000000036adee4: mov     rax,rsi
  0x00000000036adee7: jmp     36adf76h
  0x00000000036adeec: mov     qword ptr [rsp+8h],rax
  0x00000000036adef1: mov     qword ptr [rsp],0ffffffffffffffffh
  0x00000000036adef9: call    36a19a0h          ; OopMap{off=798}
    ;*synchronization entry
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@-1 (line 36)
    ;   {runtime_call}
  0x00000000036adefe: jmp     36adc15h
  0x00000000036adf03: call    369cf00h          ; OopMap{rdx=Oop off=808}
    ;*monitorenter
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@10 (line 37)
    ;   {runtime_call}
  0x00000000036adf08: mov     qword ptr [rsp+8h],rdx
  0x00000000036adf0d: mov     qword ptr [rsp],rsi
  0x00000000036adf11: call    369fca0h          ; OopMap{rdx=Oop [48]=Oop off=822}
    ;*monitorenter
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@10 (line 37)
    ;   {runtime_call}
  0x00000000036adf16: jmp     36add38h
  0x00000000036adf1b: mov     rdx,rdx
  0x00000000036adf1e: call    369d680h          ; OopMap{[48]=Oop off=835}
    ;*new  ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@17 (line 39)
    ;   {runtime_call}
  0x00000000036adf23: jmp     36addc0h
  0x00000000036adf28: lea     rax,[rsp+28h]
            0x00000000036adf2d: mov     qword ptr [rsp],rax
  0x00000000036adf31: call    36a00a0h          ;   {runtime_call}
  0x00000000036adf36: jmp     36ade50h
  0x00000000036adf3b: lea     rax,[rsp+28h]
            0x00000000036adf40: mov     qword ptr [rsp],rax
  0x00000000036adf44: call    36a00a0h          ;   {runtime_call}
  0x00000000036adf49: jmp     36adee4h
  0x00000000036adf4b: nop
  0x00000000036adf4c: nop
  0x00000000036adf4d: mov     rax,qword ptr [r15+288h]
            0x00000000036adf54: mov     r10,0h
  0x00000000036adf5e: mov     qword ptr [r15+288h],r10
  0x00000000036adf65: mov     r10,0h
  0x00000000036adf6f: mov     qword ptr [r15+290h],r10
  0x00000000036adf76: add     rsp,40h
  0x00000000036adf7a: pop     rbp
  0x00000000036adf7b: jmp     369c6a0h          ;   {runtime_call}
[Stub Code]
            0x00000000036adf80: nop                       ;   {no_reloc}
  0x00000000036adf81: nop
  0x00000000036adf82: nop
  0x00000000036adf83: nop
  0x00000000036adf84: nop
  0x00000000036adf85: mov     rbx,0h            ;   {static_stub}
  0x00000000036adf8f: jmp     36adf8fh          ;   {runtime_call}
[Exception Handler]
            0x00000000036adf94: call    369f120h          ;   {runtime_call}
  0x00000000036adf99: mov     qword ptr [rsp+0ffffffffffffffd8h],rsp
  0x00000000036adf9e: sub     rsp,80h
  0x00000000036adfa5: mov     qword ptr [rsp+78h],rax
  0x00000000036adfaa: mov     qword ptr [rsp+70h],rcx
  0x00000000036adfaf: mov     qword ptr [rsp+68h],rdx
  0x00000000036adfb4: mov     qword ptr [rsp+60h],rbx
  0x00000000036adfb9: mov     qword ptr [rsp+50h],rbp
  0x00000000036adfbe: mov     qword ptr [rsp+48h],rsi
  0x00000000036adfc3: mov     qword ptr [rsp+40h],rdi
  0x00000000036adfc8: mov     qword ptr [rsp+38h],r8
  0x00000000036adfcd: mov     qword ptr [rsp+30h],r9
  0x00000000036adfd2: mov     qword ptr [rsp+28h],r10
  0x00000000036adfd7: mov     qword ptr [rsp+20h],r11
  0x00000000036adfdc: mov     qword ptr [rsp+18h],r12
  0x00000000036adfe1: mov     qword ptr [rsp+10h],r13
  0x00000000036adfe6: mov     qword ptr [rsp+8h],r14
  0x00000000036adfeb: mov     qword ptr [rsp],r15
  0x00000000036adfef: mov     rcx,62b20df8h     ;   {external_word}
  0x00000000036adff9: mov     rdx,36adf99h      ;   {internal_word}
  0x00000000036ae003: mov     r8,rsp
  0x00000000036ae006: and     rsp,0fffffffffffffff0h
  0x00000000036ae00a: call    627fd4c0h         ;   {runtime_call}
  0x00000000036ae00f: hlt
[Deopt Handler Code]
            0x00000000036ae010: mov     r10,36ae010h      ;   {section_word}
  0x00000000036ae01a: push    r10
  0x00000000036ae01c: jmp     35e7340h          ;   {runtime_call}
  0x00000000036ae021: hlt
  0x00000000036ae022: hlt
  0x00000000036ae023: hlt
  0x00000000036ae024: hlt
  0x00000000036ae025: hlt
  0x00000000036ae026: hlt
  0x00000000036ae027: hlt
    Decoding compiled method 0x00000000036ad250:
    Code:
    Argument 0 is unknown.RIP: 0x36ad3c0 Code size: 0x00000428
            [Entry Point]
            [Verified Entry Point]
            [Constants]
            # {method} {0x000000001cad2a78} 'getInstance' '()Lcom/kevin/multithreading/geektime/other/Singleton;' in 'com/kevin/multithreading/geektime/other/Singleton'
            #           [sp+0x50]  (sp of caller)
            0x00000000036ad3c0: mov     dword ptr [rsp+0ffffffffffffa000h],eax
  0x00000000036ad3c7: push    rbp
  0x00000000036ad3c8: sub     rsp,40h           ;*synchronization entry
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@-1 (line 36)

            0x00000000036ad3cc: mov     r10,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad3d6: mov     r11d,dword ptr [r10+60h]
    ;*getstatic singleton
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@0 (line 36)

            0x00000000036ad3da: test    r11d,r11d
  0x00000000036ad3dd: je      36ad400h          ;*ifnonnull
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@3 (line 36)

            0x00000000036ad3df: mov     r10,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad3e9: mov     r10d,dword ptr [r10+60h]
            0x00000000036ad3ed: mov     rax,r10
  0x00000000036ad3f0: shl     rax,3h            ;*getstatic singleton
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@37 (line 43)

            0x00000000036ad3f4: add     rsp,40h
  0x00000000036ad3f8: pop     rbp
  0x00000000036ad3f9: test    dword ptr [2600000h],eax
    ;   {poll_return}
  0x00000000036ad3ff: ret
  0x00000000036ad400: mov     rax,qword ptr [r10]
            0x00000000036ad403: mov     r10,rax
  0x00000000036ad406: and     r10,7h
  0x00000000036ad40a: cmp     r10,5h
  0x00000000036ad40e: jne     36ad5f9h
  0x00000000036ad414: mov     r11d,0f80003dfh   ;   {metadata('java/lang/Class')}
  0x00000000036ad41a: mov     r10,0h
  0x00000000036ad424: lea     r10,[r10+r11*8]
            0x00000000036ad428: mov     r10,qword ptr [r10+0a8h]
            0x00000000036ad42f: mov     r11,r10
  0x00000000036ad432: or      r11,r15
  0x00000000036ad435: mov     r8,r11
  0x00000000036ad438: xor     r8,rax
  0x00000000036ad43b: test    r8,0ffffffffffffff87h
  0x00000000036ad442: jne     36ad679h          ;*monitorenter
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@10 (line 37)

            0x00000000036ad448: mov     r10,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad452: mov     r10d,dword ptr [r10+60h]
    ;*getstatic singleton
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@11 (line 38)

            0x00000000036ad456: mov     ebp,7h
  0x00000000036ad45b: test    r10d,r10d
  0x00000000036ad45e: je      36ad553h          ;*monitorexit
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@28 (line 41)

            0x00000000036ad464: mov     r10,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad46e: and     rbp,qword ptr [r10]
            0x00000000036ad471: cmp     rbp,5h
  0x00000000036ad475: je      36ad3dfh
  0x00000000036ad47b: mov     r11,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad485: lea     rax,[rsp+30h]
            0x00000000036ad48a: mov     r10,qword ptr [r11]
            0x00000000036ad48d: cmp     qword ptr [rax],0h
  0x00000000036ad494: je      36ad52ch
  0x00000000036ad49a: test    r10d,2h
  0x00000000036ad4a1: je      36ad524h
  0x00000000036ad4a7: mov     rax,qword ptr [r10+16h]
            0x00000000036ad4ab: xor     rax,r15
  0x00000000036ad4ae: or      rax,qword ptr [r10+26h]
            0x00000000036ad4b2: jne     36ad52ch
  0x00000000036ad4b8: mov     rax,qword ptr [r10+36h]
            0x00000000036ad4bc: or      rax,qword ptr [r10+3eh]
            0x00000000036ad4c0: jne     36ad4d3h
  0x00000000036ad4c6: mov     qword ptr [r10+16h],0h
  0x00000000036ad4ce: jmp     36ad52ch
  0x00000000036ad4d3: cmp     qword ptr [r10+46h],0h
  0x00000000036ad4db: je      36ad512h
  0x00000000036ad4e1: mov     qword ptr [r10+16h],0h
  0x00000000036ad4e9: lock add dword ptr [rsp],0h
  0x00000000036ad4ee: cmp     qword ptr [r10+46h],0h
  0x00000000036ad4f6: jne     36ad51ah
  0x00000000036ad4fc: mov     rax,0h
  0x00000000036ad506: lock cmpxchg qword ptr [r10+16h],r15
  0x00000000036ad50c: jne     36ad51ah
  0x00000000036ad512: or      eax,1h
  0x00000000036ad515: jmp     36ad52ch
  0x00000000036ad51a: test    eax,0h
  0x00000000036ad51f: jmp     36ad52ch
  0x00000000036ad524: mov     r10,qword ptr [rax]
            0x00000000036ad527: lock cmpxchg qword ptr [r11],r10
  0x00000000036ad52c: je      36ad3dfh
  0x00000000036ad532: mov     rcx,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad53c: lea     rdx,[rsp+30h]     ;*monitorenter
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@10 (line 37)

            0x00000000036ad541: mov     r10,6273aef0h
  0x00000000036ad54b: call indirect r10         ;*monitorexit
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@28 (line 41)

            0x00000000036ad54e: jmp     36ad3dfh
  0x00000000036ad553: mov     rax,qword ptr [r15+60h]
            0x00000000036ad557: mov     r10,rax
  0x00000000036ad55a: add     r10,10h
  0x00000000036ad55e: cmp     r10,qword ptr [r15+70h]
            0x00000000036ad562: jnb     36ad5d9h
  0x00000000036ad564: mov     qword ptr [r15+60h],r10
  0x00000000036ad568: prefetchnta byte ptr [r10+100h]
            0x00000000036ad570: mov     r10d,0f800c105h   ;   {metadata('com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad576: shl     r10,3h
  0x00000000036ad57a: mov     r10,qword ptr [r10+0a8h]
            0x00000000036ad581: mov     qword ptr [rax],r10
  0x00000000036ad584: mov     dword ptr [rax+8h],0f800c105h
    ;   {metadata('com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad58b: mov     dword ptr [rax+0ch],r12d
  0x00000000036ad58f: mov     r10,rax
  0x00000000036ad592: mov     qword ptr [rsp+20h],r10  ;*new
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@17 (line 39)

            0x00000000036ad597: mov     rdx,r10
  0x00000000036ad59a: nop
  0x00000000036ad59b: call    35e5ee0h          ; OopMap{[32]=Oop off=480}
    ;*invokespecial <init>
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@21 (line 39)
    ;   {optimized virtual_call}
  0x00000000036ad5a0: mov     r10,qword ptr [rsp+20h]
            0x00000000036ad5a5: shr     r10,3h
  0x00000000036ad5a9: mov     r11,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad5b3: mov     dword ptr [r11+60h],r10d
  0x00000000036ad5b7: mov     r10,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad5c1: shr     r10,9h
  0x00000000036ad5c5: mov     r11d,0f360000h
  0x00000000036ad5cb: mov     byte ptr [r11+r10],r12l
  0x00000000036ad5cf: lock add dword ptr [rsp],0h  ;*putstatic singleton
            ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@24 (line 39)

            0x00000000036ad5d4: jmp     36ad464h
  0x00000000036ad5d9: mov     rdx,7c0060828h    ;   {metadata('com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad5e3: call    36a4b20h          ; OopMap{off=552}
    ;*new  ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@17 (line 39)
    ;   {runtime_call}
  0x00000000036ad5e8: jmp     36ad58fh
  0x00000000036ad5ea: mov     r11,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad5f4: lock cmpxchg qword ptr [r11],r10
  0x00000000036ad5f9: mov     r11,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad603: lea     rbx,[rsp+30h]
            0x00000000036ad608: mov     rax,qword ptr [r11]
            0x00000000036ad60b: test    eax,2h
  0x00000000036ad610: jne     36ad639h
  0x00000000036ad616: or      eax,1h
  0x00000000036ad619: mov     qword ptr [rbx],rax
  0x00000000036ad61c: lock cmpxchg qword ptr [r11],rbx
  0x00000000036ad621: je      36ad656h
  0x00000000036ad627: sub     rax,rsp
  0x00000000036ad62a: and     rax,0fffffffffffff007h
  0x00000000036ad631: mov     qword ptr [rbx],rax
  0x00000000036ad634: jmp     36ad656h
  0x00000000036ad639: mov     qword ptr [rbx],3h
  0x00000000036ad640: mov     rbx,rax
  0x00000000036ad643: mov     rax,qword ptr [rax+16h]
            0x00000000036ad647: test    rax,rax
  0x00000000036ad64a: jne     36ad656h
  0x00000000036ad650: lock cmpxchg qword ptr [rbx+16h],r15
  0x00000000036ad656: nop
  0x00000000036ad657: je      36ad448h
  0x00000000036ad65d: mov     rdx,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad667: lea     r8,[rsp+30h]
            0x00000000036ad66c: nop
  0x00000000036ad66f: call    36a2ee0h          ; OopMap{off=692}
    ;*monitorenter
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@10 (line 37)
    ;   {runtime_call}
  0x00000000036ad674: jmp     36ad448h
  0x00000000036ad679: test    r8,7h
  0x00000000036ad680: jne     36ad5eah
  0x00000000036ad686: test    r8,300h
  0x00000000036ad68d: jne     36ad69ch
  0x00000000036ad68f: and     rax,37fh
  0x00000000036ad696: mov     r11,rax
  0x00000000036ad699: or      r11,r15
  0x00000000036ad69c: mov     r10,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad6a6: lock cmpxchg qword ptr [r10],r11
  0x00000000036ad6ab: jne     36ad65dh
  0x00000000036ad6ad: jmp     36ad448h          ;*new  ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@17 (line 39)

            0x00000000036ad6b2: mov     rbx,rax
  0x00000000036ad6b5: jmp     36ad6bah          ;*invokespecial <init>
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@21 (line 39)

            0x00000000036ad6b7: mov     rbx,rax
  0x00000000036ad6ba: mov     r10,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad6c4: and     rbp,qword ptr [r10]
            0x00000000036ad6c7: cmp     rbp,5h
  0x00000000036ad6cb: jne     36ad6dah          ;*monitorexit
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@34 (line 41)

            0x00000000036ad6cd: mov     rdx,rbx
  0x00000000036ad6d0: add     rsp,40h
  0x00000000036ad6d4: pop     rbp
  0x00000000036ad6d5: jmp     36a2de0h          ;   {runtime_call}
  0x00000000036ad6da: mov     r11,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad6e4: lea     rax,[rsp+30h]
            0x00000000036ad6e9: mov     r10,qword ptr [r11]
            0x00000000036ad6ec: cmp     qword ptr [rax],0h
  0x00000000036ad6f3: je      36ad78bh
  0x00000000036ad6f9: test    r10d,2h
  0x00000000036ad700: je      36ad783h
  0x00000000036ad706: mov     rax,qword ptr [r10+16h]
            0x00000000036ad70a: xor     rax,r15
  0x00000000036ad70d: or      rax,qword ptr [r10+26h]
            0x00000000036ad711: jne     36ad78bh
  0x00000000036ad717: mov     rax,qword ptr [r10+36h]
            0x00000000036ad71b: or      rax,qword ptr [r10+3eh]
            0x00000000036ad71f: jne     36ad732h
  0x00000000036ad725: mov     qword ptr [r10+16h],0h
  0x00000000036ad72d: jmp     36ad78bh
  0x00000000036ad732: cmp     qword ptr [r10+46h],0h
  0x00000000036ad73a: je      36ad771h
  0x00000000036ad740: mov     qword ptr [r10+16h],0h
  0x00000000036ad748: lock add dword ptr [rsp],0h
  0x00000000036ad74d: cmp     qword ptr [r10+46h],0h
  0x00000000036ad755: jne     36ad779h
  0x00000000036ad75b: mov     rax,0h
  0x00000000036ad765: lock cmpxchg qword ptr [r10+16h],r15
  0x00000000036ad76b: jne     36ad779h
  0x00000000036ad771: or      eax,1h
  0x00000000036ad774: jmp     36ad78bh
  0x00000000036ad779: test    eax,0h
  0x00000000036ad77e: jmp     36ad78bh
  0x00000000036ad783: mov     r10,qword ptr [rax]
            0x00000000036ad786: lock cmpxchg qword ptr [r11],r10
  0x00000000036ad78b: je      36ad6cdh
  0x00000000036ad791: mov     rcx,76ab489d0h    ;   {oop(a 'java/lang/Class' = 'com/kevin/multithreading/geektime/other/Singleton')}
  0x00000000036ad79b: lea     rdx,[rsp+30h]     ;*monitorenter
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@10 (line 37)

            0x00000000036ad7a0: mov     r10,6273aef0h
  0x00000000036ad7aa: call indirect r10         ;*monitorexit
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@34 (line 41)

            0x00000000036ad7ad: jmp     36ad6cdh          ;*monitorexit
    ; - com.kevin.multithreading.geektime.other.Singleton::getInstance@28 (line 41)

            0x00000000036ad7b2: hlt
  0x00000000036ad7b3: hlt
  0x00000000036ad7b4: hlt
  0x00000000036ad7b5: hlt
  0x00000000036ad7b6: hlt
  0x00000000036ad7b7: hlt
  0x00000000036ad7b8: hlt
  0x00000000036ad7b9: hlt
  0x00000000036ad7ba: hlt
  0x00000000036ad7bb: hlt
  0x00000000036ad7bc: hlt
  0x00000000036ad7bd: hlt
  0x00000000036ad7be: hlt
  0x00000000036ad7bf: hlt
[Stub Code]
            0x00000000036ad7c0: mov     rbx,0h            ;   {no_reloc}
  0x00000000036ad7ca: jmp     36ad7cah          ;   {runtime_call}
[Exception Handler]
            0x00000000036ad7cf: jmp     369cba0h          ;   {runtime_call}
[Deopt Handler Code]
            0x00000000036ad7d4: call    36ad7d9h
  0x00000000036ad7d9: sub     qword ptr [rsp],5h
  0x00000000036ad7de: jmp     35e7340h          ;   {runtime_call}
  0x00000000036ad7e3: hlt
  0x00000000036ad7e4: hlt
  0x00000000036ad7e5: hlt
  0x00000000036ad7e6: hlt
  0x00000000036ad7e7: hlt
    com.kevin.multithreading.geektime.other.Singleton@3e3abc88
    Java HotSpot(TM) 64-Bit Server VM warning: PrintAssembly is enabled; turning on DebugNonSafepoints to gain additional output

    Process finished with exit code 0
*/

}
