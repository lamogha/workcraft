package org.workcraft.plugins.petrify.tasks;

public class PetrifySynthesisResult {
    private final String equations;
    private final String verilog;
    private final String log;
    private final String stdout;
    private final String stderr;

    public PetrifySynthesisResult(String log, String equations, String verilog, String stdout, String stderr) {
        this.log = log;
        this.equations = equations;
        this.verilog = verilog;
        this.stdout = stdout;
        this.stderr = stderr;
    }

    public String getLog() {
        return this.log;
    }

    public String getEquation() {
        return this.equations;
    }

    public String getVerilog() {
        return this.verilog;
    }

    public String getStdout() {
        return this.stdout;
    }

    public String getStderr() {
        return this.stderr;
    }
}
