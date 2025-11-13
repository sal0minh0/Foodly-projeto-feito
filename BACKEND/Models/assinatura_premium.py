from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class AssinaturaPremium:
    id: Optional[int]
    cliente_id: int
    plano_id: int
    status: str                         # 'ativa', 'cancelada', 'expirada', 'pendente'
    data_inicio: datetime
    data_fim: Optional[datetime] = None
    renovacao_automatica: bool = True
    metodo_pagamento: Optional[str] = None        # ex: 'cartao_credito', 'pix'
    referencia_pagamento: Optional[str] = None    # ID da transação no gateway
    criado_em: Optional[datetime] = None
