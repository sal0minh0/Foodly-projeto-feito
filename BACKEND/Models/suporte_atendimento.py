from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class SuporteAtendimento:
    id: Optional[int]
    usuario_id: int
    assunto: Optional[str]
    status: str                             # 'aberto', 'em_atendimento', 'encerrado'
    criado_em: Optional[datetime] = None
    encerrado_em: Optional[datetime] = None
