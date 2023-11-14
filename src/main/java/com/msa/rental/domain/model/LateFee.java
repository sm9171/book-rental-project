package com.msa.rental.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LateFee {
	private long point;

	public LateFee(long point) {
		this.point = point;
	}

	public static LateFee createLateFee() {
		return new LateFee(0);
	}

	public LateFee addPoint(long point) {
		return new LateFee(this.point + point);
	}

	public LateFee removePoint(long point) throws Exception {
		if (point > this.point) {
			throw new Exception("보유한 포인트보다 커서 삭제할 수 없습니다.");
		}
		return new LateFee(this.point - point);
	}
}
