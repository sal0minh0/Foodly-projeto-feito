from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class PlanoPremium:
    id: Optional[int]
    nome: str
    descricao: Optional[str]
    valor_mensal: float
    duracao_dias: int = 30
    ativo: bool = True
    criado_em: Optional[datetime] = None
