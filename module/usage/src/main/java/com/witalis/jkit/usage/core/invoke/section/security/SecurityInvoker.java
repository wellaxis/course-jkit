package com.witalis.jkit.usage.core.invoke.section.security;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.security.*;
import java.security.spec.*;
import java.util.Random;

/**
 * Desc: secure management
 * User: Wellaxis
 * Date: 2022/01/26
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class SecurityInvoker extends Invoker {

    public SecurityInvoker() {
        setTitle("Security chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // keys
        log.info("## Security");
        invokeSecurity();
    }

    /**
     * Basic postulates of objects.
     */
    private void invokeBasis() {
        log.info("Security aspects in Java programming.");
    }

    /**
     * Key generator operations.
     */
    private void invokeSecurity() {
        // secure key generation
        try {
            final int DEFAULT_RSA_KEY_SIZE_BITS = 2048;
            final Random random = new Random(Long.MAX_VALUE);

            // generator
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            // specification
            AlgorithmParameterSpec parameters = new MGF1ParameterSpec("secure");
            AlgorithmParameterSpec specification = new RSAKeyGenParameterSpec(
                DEFAULT_RSA_KEY_SIZE_BITS,
                RSAKeyGenParameterSpec.F4
            );
            // initialization
            byte[] seed = new byte[2048];
            random.nextBytes(seed);
            SecureRandom secureRandom = new SecureRandom(seed);
            keyGenerator.initialize(specification, secureRandom);
            // generation
            KeyPair pair = keyGenerator.genKeyPair();
            PublicKey publicKey = pair.getPublic();
            PrivateKey privateKey = pair.getPrivate();
            // information
            log.info(" [*] Generator: {}", keyGenerator);
            log.info(" [+] Public Key: {}", publicKey);
            log.info(" [-] Private Key: {}", privateKey);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            log.error("Security errors, {}", e.getLocalizedMessage());
        }
    }
}
