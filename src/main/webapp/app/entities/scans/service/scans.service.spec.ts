import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IScans } from '../scans.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../scans.test-samples';

import { ScansService, RestScans } from './scans.service';

const requireRestSample: RestScans = {
  ...sampleWithRequiredData,
  createdTime: sampleWithRequiredData.createdTime?.toJSON(),
  scannedTime: sampleWithRequiredData.scannedTime?.toJSON(),
  inspectedTime: sampleWithRequiredData.inspectedTime?.toJSON(),
  modifiedTime: sampleWithRequiredData.modifiedTime?.toJSON(),
  ejectedTime: sampleWithRequiredData.ejectedTime?.toJSON(),
};

describe('Scans Service', () => {
  let service: ScansService;
  let httpMock: HttpTestingController;
  let expectedResult: IScans | IScans[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ScansService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Scans', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const scans = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(scans).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Scans', () => {
      const scans = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(scans).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Scans', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Scans', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Scans', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addScansToCollectionIfMissing', () => {
      it('should add a Scans to an empty array', () => {
        const scans: IScans = sampleWithRequiredData;
        expectedResult = service.addScansToCollectionIfMissing([], scans);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(scans);
      });

      it('should not add a Scans to an array that contains it', () => {
        const scans: IScans = sampleWithRequiredData;
        const scansCollection: IScans[] = [
          {
            ...scans,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addScansToCollectionIfMissing(scansCollection, scans);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Scans to an array that doesn't contain it", () => {
        const scans: IScans = sampleWithRequiredData;
        const scansCollection: IScans[] = [sampleWithPartialData];
        expectedResult = service.addScansToCollectionIfMissing(scansCollection, scans);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(scans);
      });

      it('should add only unique Scans to an array', () => {
        const scansArray: IScans[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const scansCollection: IScans[] = [sampleWithRequiredData];
        expectedResult = service.addScansToCollectionIfMissing(scansCollection, ...scansArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const scans: IScans = sampleWithRequiredData;
        const scans2: IScans = sampleWithPartialData;
        expectedResult = service.addScansToCollectionIfMissing([], scans, scans2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(scans);
        expect(expectedResult).toContain(scans2);
      });

      it('should accept null and undefined values', () => {
        const scans: IScans = sampleWithRequiredData;
        expectedResult = service.addScansToCollectionIfMissing([], null, scans, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(scans);
      });

      it('should return initial array if no Scans is added', () => {
        const scansCollection: IScans[] = [sampleWithRequiredData];
        expectedResult = service.addScansToCollectionIfMissing(scansCollection, undefined, null);
        expect(expectedResult).toEqual(scansCollection);
      });
    });

    describe('compareScans', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareScans(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareScans(entity1, entity2);
        const compareResult2 = service.compareScans(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareScans(entity1, entity2);
        const compareResult2 = service.compareScans(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareScans(entity1, entity2);
        const compareResult2 = service.compareScans(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
