# Comunicação Arduino via UDP sem shield
*Javaduino* é uma ferramenta simples desenvolvida em Java que converte comunicação serial do Arduino para pacotes UDP em broadcast na rede.<br/>

# Motivação
Fez-se necessário substituir a necessidade de uma shield para comunicação do Arduino em rede, reduzindo assim custos de projetos em fase de testes.<br/>

# Aplicações
Várias ferramentas para Android disponibiizam comunicação UDP com outras plataformas, sendo assim um protocolo versátil e de vasta aplicabilidade.<br/>

# Operação UDP & Serial
A aplicação funciona monitorando atividades na porta de comunicação serial, geralmente para o Arduino, e as envia para endereço de broadcast de todas as interfaces de rede.<br/>
Enquanto isso, monitora atividades de rede noutra porta, para então direcioná-las ao Arduino pela porta serial com o bitrate especificado.

# Dados padrão
Porta de broadcast do *Javaduino*: 11888.<br/>
Porta de entrada UDP do *Javaduino*: 11889.<br/>
Porta de comunicação é automática para dispositivos "Serial".<br/>

# Modificações:
# Código
*Favor referir o repositório original.*<br/>
É necessária a contínua melhoria do código, uma vez que o desempenho pode ser melhorado.<br/>
# Portas e Bitrate
Configurações de portas dos protocolos e do bitrate são feitas no arquivo *Main.java*.<br/>
Quaisquer outras alterações podem ser feitas nos respectivos arquivos.<br/>
