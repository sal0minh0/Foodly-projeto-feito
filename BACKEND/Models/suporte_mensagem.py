from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class SuporteMensagem:
    id: Optional[int]
    atendimento_id: int
    remetente_tipo: str                    # 'usuario' ou 'atendente'
    remetente_usuario_id: Optional[int]
    mensagem: str
    enviado_em: Optional[datetime] = None
