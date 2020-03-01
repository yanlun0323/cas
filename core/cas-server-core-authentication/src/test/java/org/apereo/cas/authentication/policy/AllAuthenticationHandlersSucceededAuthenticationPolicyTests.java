package org.apereo.cas.authentication.policy;

import org.apereo.cas.authentication.CoreAuthenticationTestUtils;
import org.apereo.cas.authentication.DefaultAuthenticationBuilder;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.SimpleTestUsernamePasswordAuthenticationHandler;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is {@link AllAuthenticationHandlersSucceededAuthenticationPolicyTests}.
 *
 * @author Misagh Moayyed
 * @since 6.2.0
 */
public class AllAuthenticationHandlersSucceededAuthenticationPolicyTests {
    @Test
    public void verifyOperationPrevented() {
        val input = new AllAuthenticationHandlersSucceededAuthenticationPolicy();
        val builder = new DefaultAuthenticationBuilder(CoreAuthenticationTestUtils.getPrincipal());
        val authn = builder.addFailure("Prevented", new PreventedException("error")).build();
        assertFalse(input.isSatisfiedBy(authn, Set.of()));
    }

    @Test
    public void verifyMismatch() {
        val input = new AllAuthenticationHandlersSucceededAuthenticationPolicy();
        val authn = new DefaultAuthenticationBuilder(CoreAuthenticationTestUtils.getPrincipal()).build();
        assertFalse(input.isSatisfiedBy(authn, Set.of(new SimpleTestUsernamePasswordAuthenticationHandler())));
    }
}
