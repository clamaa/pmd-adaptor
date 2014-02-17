pmd-adaptor
===========
Base On PMD-5.5.
去除了所有关于PMD报表展现以及其他逻辑部分，仅保留PMD的验证代码部分逻辑。
保留了PMD中自带的基本规则。

可以直接参考net.sourceforge.pmd.SourceCodeProcessor类作为入口，检查代码的规则仍然与PMD一致。
